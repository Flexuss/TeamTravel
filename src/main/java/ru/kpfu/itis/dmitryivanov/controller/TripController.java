package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.repository.PlaceRepository;
import ru.kpfu.itis.dmitryivanov.repository.UserRepository;
import ru.kpfu.itis.dmitryivanov.requests.PlaceRequestJson;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.TripService;

import java.util.ArrayList;

/**
 * Created by Dmitry on 13.12.2017.
 */

@RestController
@RequestMapping(value = "/trip")
public class TripController extends ResponseCreator {

    @Autowired
    TripService tripService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    PlaceRepository placeRepository;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/new_trip", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestBody RequestNewTripJson requestNewTripJson){
        boolean isPrivate = requestNewTripJson.isPrivate();
        Trip trip;
        if (isPrivate)
            trip = new Trip(securityService.getCurrentUser(),
                    requestNewTripJson.getName(),
                    requestNewTripJson.getInfo(),
                    requestNewTripJson.getFirstPlace(),
                    requestNewTripJson.getLastPlace(),
                    requestNewTripJson.getPlaces(),
                    requestNewTripJson.getMaxUserCount(),
                    requestNewTripJson.isPrivate(),
                    requestNewTripJson.getPassword());
        else
            trip = new Trip(securityService.getCurrentUser(),
                    requestNewTripJson.getName(),
                    requestNewTripJson.getInfo(),
                    requestNewTripJson.getFirstPlace(),
                    requestNewTripJson.getLastPlace(),
                    requestNewTripJson.getPlaces(),
                    requestNewTripJson.getMaxUserCount(),
                    requestNewTripJson.isPrivate(),
                    "");
        tripService.add(trip);
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<TripResponse>>> searchTrip(@RequestParam(value = "name", required = true) String name){
        ArrayList<TripResponse> tripResponse = new ArrayList<>();
        for(int i=0;i<5;i++){
            tripResponse.add(new TripResponse());
        }
        return createGoodResponse(tripResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_info", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<TripResponse>> getTripInfo(@RequestParam(value = "id", required = true) Long id){
        Trip trip = tripService.findOneById(id);
        return createGoodResponse(new TripResponse());
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_users", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<UserResponse>>> getTripUsers(@RequestParam(value = "id", required = true) Long id){
        ArrayList<UserResponse> users = new ArrayList<>();
        for(int i=0;i<5;i++){
            users.add(new UserResponse());
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_places", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<PlaceRequestJson>>> getTripPlaces(@RequestParam(value = "id", required = true) Long id){
        ArrayList<PlaceRequestJson> places = new ArrayList<>();
        for(int i=0;i<5;i++){
            places.add(new PlaceRequestJson());
        }
        return createGoodResponse(places);
    }
}
