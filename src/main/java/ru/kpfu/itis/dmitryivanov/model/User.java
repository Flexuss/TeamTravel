package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
public class User extends AbstractEntity {

    private String username;

    private String password;

    private String country;

    private String fio;

    private Integer age;

    private String about;

    private Trip trips;

    private User friends;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Trip getTrips() {
        return trips;
    }

    public void setTrips(Trip trips) {
        this.trips = trips;
    }

    public User getFriends() {
        return friends;
    }

    public void setFriends(User friends) {
        this.friends = friends;
    }
}
