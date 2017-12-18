package ru.kpfu.itis.dmitryivanov.response;

import ru.kpfu.itis.dmitryivanov.model.User;

/**
 * Created by Dmitry on 14.12.2017.
 */
public class UserResponse {
    private Long id;

    private Long image;

    private String username;

    private String fio;

    private boolean isAllreadyFriend;

    public UserResponse(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setFio(user.getFio());
        if(user.getImage()!=null) {
            this.setImage(user.getImage().getId());
        }else this.setImage(-1L);
        this.setAllreadyFriend(false);
    }

    public boolean isAllreadyFriend() {
        return isAllreadyFriend;
    }

    public void setAllreadyFriend(boolean allreadyFriend) {
        isAllreadyFriend = allreadyFriend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
