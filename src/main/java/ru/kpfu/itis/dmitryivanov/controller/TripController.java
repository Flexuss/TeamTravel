package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/new_trip", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestPart("trip") RequestNewTripJson requestNewTripJson/*,
                                                             @RequestPart("photo") MultipartFile file*/){
        Trip trip = tripService.createNewTrip(requestNewTripJson, securityService.getCurrentUser());
        ArrayList<Place> places = placeService.createPlaces(requestNewTripJson.getPlaces(), trip);
        trip.setPlaces(places);
        User user = securityService.getCurrentUser();
        List<Trip> trips = user.getTrips();
        trips.add(trip);
        user.setTrips(trips);
//        if(!file.isEmpty()){
//            try {
//                String name = String.valueOf("asdasdasdasds");
//                byte[] bytes;
//                bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(this.getClass().getClassLoader().getResource("")+"//"+name)));
//                stream.write(bytes);
//                stream.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        userService.save(user);
        tripService.save(trip);
        return createGoodResponse("Trip save success");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<TripResponse>>> searchTrip(@RequestParam(value = "placeName", required = true) String placeName,
                                                                      @RequestParam(value = "startDate", required = true)Date startDate){
        List<Place> places = placeService.findAllByName(placeName);
        List<Trip> trips = new ArrayList<>();
        for(Place place:places){
            Trip trip = tripService.findOneByPlace(place);
            if(trip.getStartDate().getTime()==startDate.getTime()&&trip.getEndDate().getTime()<new Date().getTime()){
                trips.add(trip);
            }
        }
        List<TripResponse> tripResponse = TripResponse.getTrips(trips);
        return createGoodResponse(tripResponse);
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
        List<TripResponse> tripResponse = TripResponse.getTrips(trips);
        return createGoodResponse(tripResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user_trips", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<TripResponse>>> myTrips(@RequestParam(name = "id", required = true) Long id){
        List<Trip> trips = userService.findOneById(id).getTrips();
        List<TripResponse> tripResponse = TripResponse.getTrips(trips);
        return createGoodResponse(tripResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/join_to_trip", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<String>> joinToTrip(@RequestParam(name = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        Trip trip = tripService.findOneById(id);
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
}
