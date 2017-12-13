package ru.kpfu.itis.dmitryivanov;

/**
 * Created by Dmitry on 13.12.2017.
 */
public class RequestUserLoginJson {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
