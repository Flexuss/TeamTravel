package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Friend;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.FriendRepository;
import ru.kpfu.itis.dmitryivanov.service.FriendService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 16.12.2017.
 */

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository friendRepository;

    @Override
    public List<User> getFriendsByUser(User currentUser) {
        List<Friend> friends = friendRepository.findAllByUser(currentUser);
        List<User> users = new ArrayList<>();
        for(Friend friend:friends){
            users.add(friend.getFriend());
        }
        return users;
    }

    @Override
    public void save(Friend friend) {
        friendRepository.save(friend);
    }
}
