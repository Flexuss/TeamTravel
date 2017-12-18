package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.requests.PlaceRequestJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */
public interface PlaceService {
    ArrayList<Place> createPlaces(List<PlaceRequestJson> places, Trip trip);

    List<Place> findAllByName(String placeName);
}
