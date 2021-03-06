package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Invites;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.InviteRepository;
import ru.kpfu.itis.dmitryivanov.service.InviteService;

import java.util.List;

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

    @Override
    public List<Invites> getAllByUser(User currentUser) {
        return inviteRepository.findAllByUser(currentUser);
    }

    @Override
    public Invites findOneById(Long id) {
        return inviteRepository.findOne(id);
    }

    @Override
    public void delete(Invites invite) {
        inviteRepository.delete(invite);
    }
}
