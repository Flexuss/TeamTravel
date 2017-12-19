package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;

/**
 * Created by Dmitry on 19.12.2017.
 */

@Entity
public class Invites extends AbstractEntity {

    private User invitedByUser;

    private User user;

    private Trip trip;

    public User getInvitedByUser() {
        return invitedByUser;
    }

    public void setInvitedByUser(User invitedByUser) {
        this.invitedByUser = invitedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
