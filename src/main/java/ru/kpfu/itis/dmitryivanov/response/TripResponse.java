package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Photo;
import ru.kpfu.itis.dmitryivanov.model.Place;
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

    private Long photo;

    private Integer maxUserCount;

    private Integer currentUserCount;

    private PlaceResponse firstPlace;

    private PlaceResponse lastPlace;

    private boolean isPrivate;

    public PlaceResponse getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(PlaceResponse firstPlace) {
        this.firstPlace = firstPlace;
    }

    public PlaceResponse getLastPlace() {
        return lastPlace;
    }

    public void setLastPlace(PlaceResponse lastPlace) {
        this.lastPlace = lastPlace;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
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
        if(trips.isEmpty()){
            return tripResponse;
        }
        for(Trip trip: trips){
            Place firstPlace = trip.getPlaces().get(0);
            Place lastPlace = trip.getPlaces().get(0);
            for(Place place:trip.getPlaces()){
                if(place.getDate().getTime()<firstPlace.getDate().getTime()){
                    firstPlace=place;
                }
                if (place.getDate().getTime()>lastPlace.getDate().getTime()){
                    lastPlace=place;
                }
            }
            TripResponse newTrip = new TripResponse();
            newTrip.setName(trip.getName());
            newTrip.setInfo(trip.getInfo());
            newTrip.setMaxUserCount(trip.getMaxUserCount());
            newTrip.setCurrentUserCount(trip.getUsers().size());
            newTrip.setId(trip.getId());
            if(trip.getPhoto()!=null) {
                newTrip.setPhoto(trip.getPhoto().getId());
            }else newTrip.setPhoto(-1L);
            newTrip.setPrivate(trip.isPrivate());
            newTrip.setFirstPlace(new PlaceResponse(firstPlace));
            newTrip.setLastPlace(new PlaceResponse(lastPlace));
            tripResponse.add(newTrip);
        }
        return tripResponse;
    }
}
