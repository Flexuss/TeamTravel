package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.ArrayList;

/**
 * Created by Dmitry on 30.10.2017.
 */
public interface UserService {
    User findOneByUsername(String username);

    void save(User user);

    Object findOneByPhone(String phone);

    Object findOneByEmail(String email);

    ArrayList<User> findAllByUsername(String username);

    User findOneById(Long id);
}
