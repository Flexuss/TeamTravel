package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.*;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Place extends AbstractEntity {

    private String name;

    private String info;

    private Double lon;

    private Double lat;

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
}
