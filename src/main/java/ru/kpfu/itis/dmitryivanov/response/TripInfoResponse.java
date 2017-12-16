package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Place;
import ru.kpfu.itis.dmitryivanov.model.Trip;

import java.util.List;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class TripInfoResponse {

    private Long id;

    private String name;

    private String info;

    private Long photo;

    private List<PlaceResponse> places;

    private Integer maxUserCount;

    private Integer currentUserCount;

    private boolean isPrivate;

    public TripInfoResponse(Trip trip, List<PlaceResponse> places){
        this.setName(trip.getName());
        this.setInfo(trip.getInfo());
        this.setMaxUserCount(trip.getMaxUserCount());
        this.setCurrentUserCount(trip.getUsers().size());
        this.setPlaces(places);
        this.setId(trip.getId());
        this.setPhoto(trip.getPhoto().getId());
        this.setPrivate(trip.isPrivate());
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public List<PlaceResponse> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceResponse> places) {
        this.places = places;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCurrentUserCount() {
        return currentUserCount;
    }

    public void setCurrentUserCount(Integer currentUserCount) {
        this.currentUserCount = currentUserCount;
    }
}
