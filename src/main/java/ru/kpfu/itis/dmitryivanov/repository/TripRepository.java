package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    void save(List<Place> resultPlaces);

    ArrayList<Place> findAllByTrip(Trip trip);

    ArrayList<Trip> findAllByName(String name);
}
