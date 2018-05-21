package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);

    List<Message> getAllMessagesByChat(Chat chat);

    Message getLastMessageInChat(Chat chat);
}
