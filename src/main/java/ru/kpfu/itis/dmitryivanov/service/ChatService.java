package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.User;

public interface ChatService {
    Chat getChatById(Long chatId);

    Chat getChatByUsers(User currentUser, User user);
}
