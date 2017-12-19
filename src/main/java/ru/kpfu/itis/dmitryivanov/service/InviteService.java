package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Invites;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;

/**
 * Created by Dmitry on 19.12.2017.
 */
public interface InviteService {
    Invites save(Invites invites);

    Invites getOneByInvitedByUserAndUserAndTrip(User currentUser, User invitedUser, Trip trip);
}
