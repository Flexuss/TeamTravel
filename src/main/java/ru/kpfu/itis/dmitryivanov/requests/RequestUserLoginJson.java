package ru.kpfu.itis.dmitryivanov.requests;

/**
 * Created by Dmitry on 13.12.2017.
 */
public class RequestUserLoginJson {
    private String username;
    private String password;
    private String deviceKey;

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

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
