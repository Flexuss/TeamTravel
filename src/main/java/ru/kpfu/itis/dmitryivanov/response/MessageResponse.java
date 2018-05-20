package ru.kpfu.itis.dmitryivanov.response;

import java.util.Date;

public class MessageResponse {

    private String senderFio;
    private Date messageDate;
    private String messageText;

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
