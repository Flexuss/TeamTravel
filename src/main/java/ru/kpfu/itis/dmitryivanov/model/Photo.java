package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Dmitry on 16.12.2017.
 */

@Entity
public class Photo extends AbstractEntity {

    private String path;

    @ManyToOne
    private Place place;

    @ManyToOne
    private User user;

    @ManyToOne
    private Trip trip;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
