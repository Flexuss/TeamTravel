package ru.kpfu.itis.dmitryivanov.requests;

import ru.kpfu.itis.dmitryivanov.model.Place;

import java.util.List;

/**
 * Created by Dmitry on 13.12.2017.
 */
public class RequestNewTripJson {
    private String name;

    private String info;

    private Long photo;

    private List<PlaceRequestJson> places;

    private Integer maxUserCount;

    private boolean isPrivate;

    private String password;

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

    public List<PlaceRequestJson> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceRequestJson> places) {
        this.places = places;
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
}
