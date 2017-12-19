package ru.kpfu.itis.dmitryivanov.requests;

/**
 * Created by Dmitry on 19.12.2017.
 */
public class TripInviteRequestJson {
    private Long userId;

    private Long tripId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
