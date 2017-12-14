package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Event;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class PlaceResponse {
    private String name;

    private Double lon;

    private Double lat;

    private Date date;

    private List<Event> events;

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
