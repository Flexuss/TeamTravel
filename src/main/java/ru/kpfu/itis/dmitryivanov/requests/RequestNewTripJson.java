package ru.kpfu.itis.dmitryivanov.requests;

import ru.kpfu.itis.dmitryivanov.model.Event;
import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 13.12.2017.
 */
public class RequestNewTripJson {
    private String name;

    private String info;

    private List<Place> places;

    private Integer maxUserCount;

    private List<User> users;

    private List<Event> events;

    private Date startDate;

    private boolean isPrivate;

    private String password;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Integer getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
