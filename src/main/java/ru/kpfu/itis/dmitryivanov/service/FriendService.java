package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Friend;
import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.List;

/**
 * Created by Dmitry on 16.12.2017.
 */
public interface FriendService {
    List<User> getFriendsByUser(User currentUser);

    void save(Friend friend);

    Friend getFriendsByUserAndFriend(User currentUser, User newFriend);

    void remove(Friend friend);
}
