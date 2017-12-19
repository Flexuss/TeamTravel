package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Invites;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.List;

/**
 * Created by Dmitry on 19.12.2017.
 */

@Repository
public interface InviteRepository extends JpaRepository<Invites, Long> {
    Invites findOneByInvitedByUserAndUserAndTrip(User currentUser, User invitedUser, Trip trip);

    List<Invites> findAllByUser(User currentUser);
}
