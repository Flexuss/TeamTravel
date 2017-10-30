package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Event extends AbstractEntity {
    private String name;

    private Place place;

    private Trip trip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
