package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.Message;
import ru.kpfu.itis.dmitryivanov.repository.MessageRepository;
import ru.kpfu.itis.dmitryivanov.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessagesByChat(Chat chat) {
        List<Message> messages = messageRepository.findAllByChat(chat);
        return messages;
    }
}
