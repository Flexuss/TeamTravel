package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.response.ChatInfoResponse;

import java.util.List;

public interface ChatService {
    Chat getChatById(Long chatId);

    Chat getChatByUsers(User currentUser, User user);

    List<Chat> getAllByUser(User currentUser);
}
