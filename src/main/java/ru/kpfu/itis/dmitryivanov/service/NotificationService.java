package ru.kpfu.itis.dmitryivanov.service;


import java.io.IOException;

public interface NotificationService {

    public void sendPushNotification(Long userId, String title, String info) throws IOException;
}
