package ru.kpfu.itis.dmitryivanov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Photo;
import ru.kpfu.itis.dmitryivanov.repository.PhotoRepository;
import ru.kpfu.itis.dmitryivanov.service.PhotoService;

/**
 * Created by Dmitry on 18.12.2017.
 */

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public Photo getOneById(Long id) {
        return photoRepository.findOne(id);
    }
}
