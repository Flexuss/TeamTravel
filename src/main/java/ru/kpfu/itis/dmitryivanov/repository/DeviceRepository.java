package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device getOneByDeviceKey(String deviceKey);
}
