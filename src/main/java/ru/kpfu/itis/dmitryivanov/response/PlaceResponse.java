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

    private Long id;

    private String name;

    private Double lon;

    private Double lat;

    private Date date;

    private List<Long> photos;

    public PlaceResponse(Place place) {
        setId(place.getId());
        setName(place.getName());
        setDate(place.getDate());
        setLat(place.getLat());
        setLon(place.getLon());
        ArrayList<Long> photos = new ArrayList<>();
        for(Photo photo:place.getPhotos()){
            photos.add(photo.getId());
        }
        if (photos.isEmpty()){
            photos.add(-1L);
        }
        setPhotos(photos);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
            PlaceResponse newPlace = new PlaceResponse(place);
            placeResponse.add(newPlace);
        }
        return placeResponse;
    }
}
