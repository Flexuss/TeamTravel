package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.Date;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class UserInfoResponse {

    private Long id;

    private String phoneNumber;

    private String email;

    private Long image;

    private String username;

    private String country;

    private String fio;

    private Date birthDate;

    private String interests;

    public UserInfoResponse(User user) {
        this.setId(user.getId());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
        if(user.getImage()!=null) {
            this.setImage(user.getImage().getId());
        }else this.setImage(-1L);
        this.setUsername(user.getUsername());
        this.setCountry(user.getCountry());
        this.setFio(user.getFio());
        this.setBirthDate(user.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
