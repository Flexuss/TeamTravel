package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Device;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.DeviceRepository;
import ru.kpfu.itis.dmitryivanov.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public void setDeviceToUser(User user, String deviceKey) {
        Device device = deviceRepository.getOneByDeviceKey(deviceKey);
        if(device!=null){
            if(!device.getUser().getId().equals(user.getId())){
                device.setUser(user);
                deviceRepository.save(device);
            }
        }else {
            Device device1 = new Device();
            device1.setUser(user);
            device1.setDeviceKey(deviceKey);
            deviceRepository.save(device1);
        }
    }
}
