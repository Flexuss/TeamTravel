package ru.kpfu.itis.dmitryivanov.response;

public class ChatInfoResponse {

    private Long chatId;
    private String chatName;
    private MessageInfoResponse lastMessage;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public MessageInfoResponse getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageInfoResponse lastMessage) {
        this.lastMessage = lastMessage;
    }
}
