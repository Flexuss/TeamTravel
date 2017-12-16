package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.TripRepository;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;
import ru.kpfu.itis.dmitryivanov.service.TripService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;
    @Override
    public void add(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public Trip findOneById(Long id) {
        return tripRepository.findOne(id);
    }

    @Override
    public Trip createNewTrip(RequestNewTripJson requestNewTripJson, User creator) {
        Trip trip = new Trip();
        trip.setName(requestNewTripJson.getName());
        trip.setInfo(requestNewTripJson.getInfo());
        trip.setCreator(creator);
        trip.setMaxUserCount(requestNewTripJson.getMaxUserCount());
        trip.setPrivate(requestNewTripJson.isPrivate());
        trip.setPassword(trip.isPrivate()?"":requestNewTripJson.getPassword());
        trip = tripRepository.save(trip);
        return trip;
    }

    @Override
    public ArrayList<Trip> findAllByName(String name) {
        return tripRepository.findAllByName(name);
    }
}
