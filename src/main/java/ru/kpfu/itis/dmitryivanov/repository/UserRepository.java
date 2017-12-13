package ru.kpfu.itis.dmitryivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dmitryivanov.model.User;

import java.util.ArrayList;

/**
 * Created by Dmitry on 30.10.2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);

    User findOneByPhoneNumber(String phone);

    User findOneByEmail(String email);

    ArrayList<User> findAllByUsername(String username);
}
