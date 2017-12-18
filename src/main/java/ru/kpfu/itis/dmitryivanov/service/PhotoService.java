package ru.kpfu.itis.dmitryivanov.service;

import ru.kpfu.itis.dmitryivanov.model.Photo;

/**
 * Created by Dmitry on 18.12.2017.
 */
public interface PhotoService {
    void save(Photo photo);

    Photo getOneById(Long id);
}
