package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.UserRepository;
import ru.kpfu.itis.dmitryivanov.service.UserService;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
