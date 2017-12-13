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

    @OneToOne
    private Place place;

    @OneToOne
    private Trip trip;

    public Place getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public void setPlace(Place place) {
        this.place = place;
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
