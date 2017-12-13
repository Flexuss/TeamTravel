package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Trip;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dmitry on 30.10.2017.
 */
public interface TripService {
    void add(Trip trip);

    ArrayList<Trip> findByNameAndStartDateAndIsPrivate(String name, Date startDate, boolean isPrivate);

    Trip findOneById(Long id);
}
