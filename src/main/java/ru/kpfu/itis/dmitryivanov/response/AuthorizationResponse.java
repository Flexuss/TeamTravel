package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.User;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class AuthorizationResponse {
    private String token;

    private UserInfoResponse user;

    public AuthorizationResponse(String token, UserInfoResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoResponse getUser() {
        return user;
    }

    public void setUser(UserInfoResponse user) {
        this.user = user;
    }
}
