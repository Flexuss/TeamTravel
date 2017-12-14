package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.Trip;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
