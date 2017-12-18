package ru.kpfu.itis.dmitryivanov.model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Trip extends AbstractEntity {

    @ManyToOne
    private User creator;

    private String name;

    private String info;

    @OneToOne
    private Photo photo;

    @OneToMany
    private List<Place> places;

    private Integer maxUserCount;

    @ManyToMany
    private List<User> users;

    private boolean isPrivate;

    private String password;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Trip() {
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

    public Date getStartDate() {
        Date startDate = getPlaces().get(0).getDate();
        for(Place place: getPlaces()){
            if(place.getDate().getTime()<startDate.getTime()){
                startDate=place.getDate();
            }
        }
        return startDate;
    }

    public Date getEndDate() {
        Date endDate = getPlaces().get(0).getDate();
        for(Place place: getPlaces()){
            if(place.getDate().getTime()>endDate.getTime()){
                endDate=place.getDate();
            }
        }
        return endDate;
    }
}
