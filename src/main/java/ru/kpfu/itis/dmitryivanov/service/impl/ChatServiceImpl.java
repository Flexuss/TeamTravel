package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.ChatRepository;
import ru.kpfu.itis.dmitryivanov.service.ChatService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findOne(chatId);
    }

    @Override
    public Chat getChatByUsers(User currentUser, User user) {
        List<Chat> userChats = chatRepository.findAllByChatUsersContains(currentUser);
        for(Chat chat:userChats){
            if(chat.getChatUsers().size()==2&&chat.getChatUsers().contains(user)){
                return chat;
            }
        }
        Chat chat = new Chat();
        chat.setChatName(currentUser.getFio()+" и "+user.getFio());
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(currentUser);
        chat.setChatUsers(users);
        chatRepository.save(chat);
        return chat;
    }
}
