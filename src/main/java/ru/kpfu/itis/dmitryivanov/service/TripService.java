package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.requests.RequestNewTripJson;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dmitry on 30.10.2017.
 */
public interface TripService {
    void save(Trip trip);

    Trip findOneById(Long id);

    Trip createNewTrip(RequestNewTripJson requestNewTripJson, User creator);

    ArrayList<Trip> findAllByName(String name);

    Trip findOneByPlace(Place place);
}
