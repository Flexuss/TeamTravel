package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.ApiResponse;
import ru.kpfu.itis.dmitryivanov.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.requests.RequestTripInfoJson;
import ru.kpfu.itis.dmitryivanov.requests.RequestTripSearchJson;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.TripService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dmitry on 13.12.2017.
 */

@RestController
@RequestMapping(value = "/trip")
public class TripController extends ResponseCreator {

    @Autowired
    TripService tripService;

    @Autowired
    SecurityService securityService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/new_trip", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestBody RequestNewTripJson requestNewTripJson){
        boolean isPrivate = requestNewTripJson.isPrivate();
        Trip trip;
        if (isPrivate)
            trip = new Trip(
                    requestNewTripJson.getName(),
                    requestNewTripJson.getInfo(),
                    requestNewTripJson.getPlaces(),
                    requestNewTripJson.getMaxUserCount(),
                    requestNewTripJson.getUsers(),
                    requestNewTripJson.getEvents(),
                    requestNewTripJson.getStartDate(),
                    requestNewTripJson.isPrivate());
        else
            trip = new Trip(
                    requestNewTripJson.getName(),
                    requestNewTripJson.getInfo(),
                    requestNewTripJson.getPlaces(),
                    requestNewTripJson.getMaxUserCount(),
                    requestNewTripJson.getUsers(),
                    requestNewTripJson.getEvents(),
                    requestNewTripJson.getStartDate(),
                    requestNewTripJson.isPrivate(),
                    requestNewTripJson.getPassword());
        tripService.add(trip);
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ArrayList<Trip>>> searchTrip(@RequestParam(value = "name", required = true) String name,
                                                                   @RequestParam(value = "startDate", required = true) Date startDate,
                                                                   @RequestParam(value = "isPrivate", required = true) Boolean isPrivate){
        ArrayList<Trip> trips = tripService.findByNameAndStartDateAndIsPrivate(
                name,
                startDate,
                isPrivate);
        return createGoodResponse(trips);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/trip_info", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<Trip>> getTripInfo(@RequestParam(value = "id", required = true) Long id){
        Trip trip = tripService.findOneById(id);
        return createGoodResponse(trip);
    }
}
