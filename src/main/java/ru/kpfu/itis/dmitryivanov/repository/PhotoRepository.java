package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Photo;

/**
 * Created by Dmitry on 16.12.2017.
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
