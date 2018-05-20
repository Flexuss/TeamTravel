package ru.kpfu.itis.dmitryivanov.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserRegistrationJson;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private String phoneNumber;

    private String email;

    @OneToOne
    private Photo image;

    private String username;

    private String password;

    private String country;

    private String fio;

    private Date birthDate;

    private String interests;

    @OneToMany
    private List<Trip> trips;

    @OneToMany
    private List<Device> devices;

    @ManyToMany
    private List<Chat> chats;

    public User() {
    }

    public User(RequestUserRegistrationJson requestUserRegistrationJson) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.setUsername(requestUserRegistrationJson.getUsername());
        this.setFio(requestUserRegistrationJson.getFio());
        this.setPassword(encoder.encode(requestUserRegistrationJson.getPassword()));
        this.setEmail(requestUserRegistrationJson.getEmail());
        this.setPhoneNumber(requestUserRegistrationJson.getPhoneNumber());
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Photo getImage() {
        return image;
    }

    public void setImage(Photo image) {
        this.image = image;
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

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

}
