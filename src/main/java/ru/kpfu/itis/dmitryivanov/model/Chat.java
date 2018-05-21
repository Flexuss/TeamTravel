package ru.kpfu.itis.dmitryivanov.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Chat extends AbstractEntity {

    @ManyToMany
    private List<User> chatUsers;
    private String chatName;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(List<User> chatUsers) {
        this.chatUsers = chatUsers;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
