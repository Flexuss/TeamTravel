package ru.kpfu.itis.dmitryivanov.requests;

import java.util.Date;

/**
 * Created by Dmitry on 18.12.2017.
 */
public class ChangeUserRequestJson {
    private String country;

    private String fio;

    private Date birthDate;

    private String interests;

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
