package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dmitryivanov.model.*;
import ru.kpfu.itis.dmitryivanov.requests.TripInviteRequestJson;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.service.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dmitry on 13.12.2017.
 */

@RestController
@RequestMapping(value = "/secure/trip")
public class TripController extends ResponseCreator {

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    FriendService friendService;

    @Autowired
    PlaceService placeService;

    @Autowired
    PhotoService photoService;

    @Autowired
    InviteService inviteService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/new_trip", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestBody RequestNewTripJson requestNewTripJson){
        Trip trip = tripService.createNewTrip(requestNewTripJson, securityService.getCurrentUser());
        ArrayList<Place> places = placeService.createPlaces(requestNewTripJson.getPlaces(), trip);
        trip.setPlaces(places);
        User user = securityService.getCurrentUser();
        List<Trip> trips = user.getTrips();
        Photo photo = photoService.getOneById(requestNewTripJson.getPhoto());
        trip.setPhoto(photo);
        trips.add(trip);
        user.setTrips(trips);
        photo.setTrip(trip);
        photoService.save(photo);
        userService.save(user);
        tripService.save(trip);
        return createGoodResponse("Trip save success");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/upload_trip_photo", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<Long>> uploadTripPhoto(@RequestPart MultipartFile image){
        if(!image.isEmpty()){
            try {
                String name = UUID.randomUUID().toString();
                byte[] bytes = image.getBytes();
                File file = new File(System.getProperty("user.dir")+File.separator+name);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                String path = file.getAbsolutePath();
                Photo photo = new Photo();
                photo.setPath(path);
                photo = photoService.save(photo);
                return createGoodResponse(photo.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<TripResponse>>> searchTrip(@RequestParam(value = "placeName") String placeName){
        User currentUser = securityService.getCurrentUser();
        List<Trip> trips = tripService.findAllByName(placeName);
        List<TripResponse> tripResponses = TripResponse.getTrips(trips, currentUser);
        return createGoodResponse(tripResponses);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_info", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<TripInfoResponse>> getTripInfo(@RequestParam(value = "id", required = true) Long id){
        Trip trip = tripService.findOneById(id);
        List<PlaceResponse> placeResponse = PlaceResponse.getPlaces(trip.getPlaces());
        return createGoodResponse(new TripInfoResponse(trip, placeResponse));
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_users", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<UserResponse>>> getTripUsers(@RequestParam(value = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        Trip trip = tripService.findOneById(id);
        List<User> friends = friendService.getFriendsByUser(currentUser);
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:trip.getUsers()){
            UserResponse newUser = new UserResponse(user);
            if(friends.contains(user)){
                newUser.setAllreadyFriend(true);
            }
            users.add(newUser);
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_places", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<PlaceResponse>>> getTripPlaces(@RequestParam(value = "id", required = true) Long id){
        Trip trip = tripService.findOneById(id);
        List<PlaceResponse> places = PlaceResponse.getPlaces(trip.getPlaces());
        return createGoodResponse(places);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/my_trips", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<TripResponse>>> myTrips(){
        List<Trip> trips = securityService.getCurrentUser().getTrips();
        List<TripResponse> tripResponse = TripResponse.getTrips(trips, securityService.getCurrentUser());
        return createGoodResponse(tripResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user_trips", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<TripResponse>>> myTrips(@RequestParam(name = "id", required = true) Long id){
        List<Trip> trips = userService.findOneById(id).getTrips();
        List<TripResponse> tripResponse = TripResponse.getTrips(trips, securityService.getCurrentUser());
        return createGoodResponse(tripResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/join_to_trip", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<String>> joinToTrip(@RequestParam(name = "id", required = true) Long id,
                                                          @RequestParam(name = "password") String password){
        User currentUser = securityService.getCurrentUser();
        Trip trip = tripService.findOneById(id);
        if(trip.isPrivate()){
            if (!trip.getPassword().equals(password)){
                return createBadResponse("Wrong password");
            }
        }
        List<User> tripUsers = trip.getUsers();
        if(tripUsers.contains(currentUser)||trip.getCreator().equals(currentUser)){
            return createBadResponse("You already join in this trip");
        }
        tripUsers.add(currentUser);
        List<Trip> userTrips = currentUser.getTrips();
        userTrips.add(trip);
        userService.save(currentUser);
        tripService.save(trip);
        return createGoodResponse("Join success");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/leave_from_trip", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<String>> leaveFromTrip(@RequestParam(name = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        Trip trip = tripService.findOneById(id);
        List<User> tripUsers = trip.getUsers();
        if(!tripUsers.contains(currentUser)){
            return createBadResponse("You not member of this trip");
        }
        if(trip.getCreator().equals(currentUser)){
            return createBadResponse("You can't leave from your own trip");
        }
        tripUsers.remove(currentUser);
        currentUser.getTrips().remove(trip);
        tripService.save(trip);
        userService.save(currentUser);
        return createGoodResponse("Leave success");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/invite_to_trip", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<String>> inviteToTrip(@RequestBody TripInviteRequestJson tripInviteRequestJson){
        User currentUser = securityService.getCurrentUser();
        User invitedUser = userService.findOneById(tripInviteRequestJson.getUserId());
        Trip trip = tripService.findOneById(tripInviteRequestJson.getUserId());
        Invites invites = new Invites();
        invites.setTrip(trip);
        invites.setInvitedByUser(currentUser);
        invites.setUser(invitedUser);
        if(inviteService.getOneByInvitedByUserAndUserAndTrip(currentUser,invitedUser,trip)!=null){
            return createBadResponse("Invite already send");
        }
        inviteService.save(invites);
        return createGoodResponse("Success invited");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/accept_invite", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> acceptInvite(@RequestBody Long id){
        Invites invites = inviteService.findOneById(id);
        User currentUser = securityService.getCurrentUser();
        Trip trip = invites.getTrip();
        List<User> tripUsers = trip.getUsers();
        if(tripUsers.contains(currentUser)||trip.getCreator().equals(currentUser)){
            return createBadResponse("You already join in this trip");
        }
        tripUsers.add(currentUser);
        List<Trip> userTrips = currentUser.getTrips();
        userTrips.add(trip);
        userService.save(currentUser);
        tripService.save(trip);
        return createGoodResponse("Join success");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/reject_invite", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> rejectInvite(@RequestBody Long id){
        Invites invite = inviteService.findOneById(id);
        if(invite==null){
            return createBadResponse("Invite already deleted");
        }
        inviteService.delete(invite);
        return createGoodResponse("You reject invite");
    }
}
