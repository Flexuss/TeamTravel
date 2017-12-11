package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.User;

/**
 * Created by Dmitry on 30.10.2017.
 */
public interface UserService {
    User findOneByUsername(String username);

    void save(User user);
}
