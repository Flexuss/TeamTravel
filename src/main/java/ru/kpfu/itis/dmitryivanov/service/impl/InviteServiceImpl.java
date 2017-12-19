package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Invites;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.InviteRepository;
import ru.kpfu.itis.dmitryivanov.service.InviteService;

/**
 * Created by Dmitry on 19.12.2017.
 */

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    InviteRepository inviteRepository;

    @Override
    public Invites save(Invites invites) {
        return inviteRepository.save(invites);
    }

    @Override
    public Invites getOneByInvitedByUserAndUserAndTrip(User currentUser, User invitedUser, Trip trip) {
        return inviteRepository.findOneByInvitedByUserAndUserAndTrip(currentUser,invitedUser,trip);
    }
}
