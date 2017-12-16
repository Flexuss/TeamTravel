package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Photo;
import ru.kpfu.itis.dmitryivanov.model.Place;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 16.12.2017.
 */
public class PlaceResponse {

    private String name;

    private String info;

    private Double lon;

    private Double lat;

    private Date date;

    private List<Long> photos;

    public List<Long> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Long> photos) {
        this.photos = photos;
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

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static List<PlaceResponse> getPlaces(List<Place> places) {
        List<PlaceResponse> placeResponse = new ArrayList<>();
        for(Place place:places){
            PlaceResponse newPlace = new PlaceResponse();
            newPlace.setName(place.getName());
            newPlace.setInfo(place.getInfo());
            newPlace.setDate(place.getDate());
            newPlace.setLat(place.getLat());
            newPlace.setLon(place.getLon());
            ArrayList photos = new ArrayList();
            for(Photo photo:place.getPhotos()){
                photos.add(photo.getId());
            }
            newPlace.setPhotos(photos);
        }
        return placeResponse;
    }
}
