package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.*;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class Place extends AbstractEntity {

    private String name;

    private String info;

    @ManyToOne
    private Event event;

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
