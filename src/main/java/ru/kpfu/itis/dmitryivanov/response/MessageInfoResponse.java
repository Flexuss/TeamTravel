package ru.kpfu.itis.dmitryivanov.response;

import java.util.Date;

public class MessageInfoResponse {

    private Long messageId;
    private String senderFio;
    private Date messageDate;
    private String messageText;
    private Long senderId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderFio() {

        return senderFio;
    }

    public void setSenderFio(String senderFio) {
        this.senderFio = senderFio;
    }
}
