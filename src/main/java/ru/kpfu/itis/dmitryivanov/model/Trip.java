package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Trip extends AbstractEntity {

    private String name;

    private String info;

    @OneToMany
    private List<Place> places;

    private Integer maxUserCount;

    @OneToMany
    private List<User> users;

    @OneToMany
    private List<Event> events;

    private Date startDate;

    private boolean isPrivate;

    private String password;

    public Trip(String name, String info, List<Place> places, Integer maxUserCount, List<User> users, List<Event> events, Date startDate, boolean isPrivate, String password) {
        this.name = name;
        this.info = info;
        this.places = places;
        this.maxUserCount = maxUserCount;
        this.users = users;
        this.events = events;
        this.startDate = startDate;
        this.isPrivate = isPrivate;
        this.password = password;
    }

    public Trip(String name, String info, List<Place> places, Integer maxUserCount, List<User> users, List<Event> events, Date startDate, boolean isPrivate) {
        this.name = name;
        this.info = info;
        this.places = places;
        this.maxUserCount = maxUserCount;
        this.users = users;
        this.events = events;
        this.startDate = startDate;
        this.isPrivate = isPrivate;
    }

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

    public Integer getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Integer maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
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
