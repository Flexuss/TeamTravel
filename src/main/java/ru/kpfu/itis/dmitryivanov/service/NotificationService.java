package ru.kpfu.itis.dmitryivanov.service;


import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.Message;

import java.io.IOException;

public interface NotificationService {

    public void sendPushNotification(Long userId, Chat chat, Message message) throws IOException;
}
