package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Event extends AbstractEntity {
    private String name;

    @OneToMany
    private List<Place> places;

    @OneToOne
    private Trip trip;

    public List<Place> getPlaces() {
        return places;
    }

    public String getName() {
        return name;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
