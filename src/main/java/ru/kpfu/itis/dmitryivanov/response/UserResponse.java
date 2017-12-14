package ru.kpfu.itis.dmitryivanov.response;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class UserResponse {
    private String image;

    private String username;

    private String fio;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
