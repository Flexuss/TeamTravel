package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.dmitryivanov.model.Friend;
import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.List;

/**
 * Created by Dmitry on 16.12.2017.
 */
public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByUser(User currentUser);

    Friend findOneByUserAndFriend(User currentUser, User newFriend);
}
