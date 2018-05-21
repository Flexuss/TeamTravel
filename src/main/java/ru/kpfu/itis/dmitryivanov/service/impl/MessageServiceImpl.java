package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    @Override
    public Message getLastMessageInChat(Chat chat) {
        List<Message> messages = messageRepository.findByChatOrderByMessageDate(chat);
        Message lastMessage = messages.get(0);
        for(Message message:messages){
            if(message.getMessageDate().getTime()>lastMessage.getMessageDate().getTime()){
                lastMessage = message;
            }
        }
        return lastMessage;
    }
}
