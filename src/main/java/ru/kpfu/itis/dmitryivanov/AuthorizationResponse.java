package ru.kpfu.itis.dmitryivanov;

import ru.kpfu.itis.dmitryivanov.model.User;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class AuthorizationResponse {
    private String token;

    private User user;

    public AuthorizationResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
