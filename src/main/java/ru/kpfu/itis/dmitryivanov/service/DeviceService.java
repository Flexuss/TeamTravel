package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.User;

public interface DeviceService {
    void setDeviceToUser(User user, String deviceKey);
}
