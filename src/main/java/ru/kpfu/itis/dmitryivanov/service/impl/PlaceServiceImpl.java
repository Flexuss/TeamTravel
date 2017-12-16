package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.repository.TripRepository;
import ru.kpfu.itis.dmitryivanov.requests.PlaceRequestJson;
import ru.kpfu.itis.dmitryivanov.service.PlaceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    TripRepository tripRepository;
    @Override
    public ArrayList<Place> createPlaces(List<PlaceRequestJson> places, Trip trip) {
        List<Place> resultPlaces = new ArrayList<>();
        for(PlaceRequestJson place:places){
            Place newPlace = new Place();
            newPlace.setName(place.getName());
            newPlace.setInfo(place.getInfo());
            newPlace.setDate(place.getDate());
            newPlace.setLat(place.getLat());
            newPlace.setLon(place.getLon());
            newPlace.setTrip(trip);
            resultPlaces.add(newPlace);
        }
        tripRepository.save(resultPlaces);
        return tripRepository.findAllByTrip(trip);
    }
}
