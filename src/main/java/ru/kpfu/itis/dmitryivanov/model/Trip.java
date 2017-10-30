package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Trip extends AbstractEntity {

    private String name;

    private String info;

    private Place places;

    private Integer maxUserCount;

    private User users;

    private Event events;

    public Event getEvents() {
        return events;
    }

    public void setEvents(Event events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Place getPlaces() {
        return places;
    }

    public void setPlaces(Place places) {
        this.places = places;
    }

    public Integer getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
