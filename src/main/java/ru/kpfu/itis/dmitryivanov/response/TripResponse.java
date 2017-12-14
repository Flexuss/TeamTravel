package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Place;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class TripResponse {

    private Long id;

    private String name;

    private String info;

    private PlaceResponse firstPlace;

    private PlaceResponse lastPlace;

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
