package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 16.12.2017.
 */
public class TripResponse {
    private Long id;

    private String name;

    private String info;

    private String photo;

    private Integer maxUserCount;

    private Integer currentUserCount;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public static List<TripResponse> getTrips(List<Trip> trips) {
        ArrayList<TripResponse> tripResponse = new ArrayList<>();
        for(Trip trip: trips){
            TripResponse newTrip = new TripResponse();
            newTrip.setName(trip.getName());
            newTrip.setInfo(trip.getInfo());
            newTrip.setMaxUserCount(trip.getMaxUserCount());
            newTrip.setCurrentUserCount(trip.getUsers().size());
            newTrip.setId(trip.getId());
            newTrip.setPhoto(trip.getPhoto());
            tripResponse.add(newTrip);
        }
        return tripResponse;
    }
}
