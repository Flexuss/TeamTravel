package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.UserRepository;
import ru.kpfu.itis.dmitryivanov.requests.PlaceRequestJson;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.service.PlaceService;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.TripService;

import java.util.ArrayList;
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
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    PlaceService placeService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/new_trip", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestBody RequestNewTripJson requestNewTripJson){
        Trip trip = tripService.createNewTrip(requestNewTripJson, securityService.getCurrentUser());
        ArrayList<Place> places = placeService.createPlaces(requestNewTripJson.getPlaces(), trip);
        trip.setPlaces(places);
        tripService.add(trip);
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<TripResponse>>> searchTrip(@RequestParam(value = "name", required = true) String name){
        ArrayList<Trip> trips = tripService.findAllByName(name);
        ArrayList<TripResponse> tripResponse = TripResponse.getTrips(trips);
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
        Trip trip = tripService.findOneById(id);
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:trip.getUsers()){
            users.add(new UserResponse(user));
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
}
