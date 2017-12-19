package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.Invites;

/**
 * Created by Dmitry on 19.12.2017.
 */
public class InviteResponse {

    String username;

    Long tripId;

    public InviteResponse(Invites invites1) {
        setUsername(invites1.getInvitedByUser().getUsername());
        setTripId(invites1.getTrip().getId());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
