package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.PlaceRequestJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    ArrayList<Place> findAllByTrip(Trip trip);

    List<Place> findAllByName(String placeName);
}
