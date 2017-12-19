package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Dmitry on 19.12.2017.
 */

@Entity
public class Invites extends AbstractEntity {

    @OneToOne
    private User invitedByUser;

    @OneToOne
    private User user;

    @OneToOne
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
