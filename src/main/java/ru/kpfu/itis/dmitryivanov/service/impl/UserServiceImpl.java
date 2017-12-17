package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.UserRepository;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.ArrayList;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findOneByPhone(String phone) {
        return userRepository.findOneByPhoneNumber(phone);
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public ArrayList<User> findAllByUsername(String username) {
        return userRepository.findAllByUsernameLike("%"+username+"%");
    }

    @Override
    public User findOneById(Long id) {
        return userRepository.findOne(id);
    }
}
