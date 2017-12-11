package ru.kpfu.itis.dmitryivanov.service;


import ru.kpfu.itis.dmitryivanov.model.User;

public interface SecurityService {
    User getCurrentUser();

    String getCurrentUserUsername();

    String generateToken(String email, String password);

}
