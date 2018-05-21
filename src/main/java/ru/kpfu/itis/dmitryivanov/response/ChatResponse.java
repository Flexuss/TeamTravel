package ru.kpfu.itis.dmitryivanov.response;


import java.util.List;

public class ChatResponse {

    private String chatName;
    private Long chatId;
    private List<UserResponse> users;
    private List<MessageInfoResponse> messages;

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public List<MessageInfoResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageInfoResponse> messages) {
        this.messages = messages;
    }
}
