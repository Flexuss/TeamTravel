package ru.kpfu.itis.dmitryivanov.model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Trip extends AbstractEntity {

    private User creator;

    private String name;

    private String info;

    private String photo;

    @OneToOne
    private Place firstPlace;

    @OneToOne
    private Place lastPlace;

    @OneToMany
    private List<Place> places;

    private Integer maxUserCount;

    @ManyToMany
    private List<User> users;

    private boolean isPrivate;

    private String password;

    public Trip(User creator, String name, String info, Place firstPlace, Place lastPlace, List<Place> places, Integer maxUserCount, boolean isPrivate, String password) {
        this.creator = creator;
        this.name = name;
        this.info = info;
        this.firstPlace = firstPlace;
        this.lastPlace = lastPlace;
        this.places = places;
        this.maxUserCount = maxUserCount;
        this.isPrivate = isPrivate;
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Place getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(Place firstPlace) {
        this.firstPlace = firstPlace;
    }

    public Place getLastPlace() {
        return lastPlace;
    }

    public void setLastPlace(Place lastPlace) {
        this.lastPlace = lastPlace;
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
}
