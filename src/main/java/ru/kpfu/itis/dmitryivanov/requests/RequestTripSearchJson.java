package ru.kpfu.itis.dmitryivanov.requests;

import java.util.Date;

/**
 * Created by Dmitry on 13.12.2017.
 */
public class RequestTripSearchJson {
    private String name;

    private Date startDate;

    private boolean isPrivate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
