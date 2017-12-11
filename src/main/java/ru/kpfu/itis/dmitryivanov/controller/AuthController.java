package ru.kpfu.itis.dmitryivanov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.dmitryivanov.ApiResponse;
import ru.kpfu.itis.dmitryivanov.RequestUserJson;
import ru.kpfu.itis.dmitryivanov.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.Validator;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.UUID;

/**
 * Created by Dmitry on 06.11.2017.
 */
public class AuthController extends ResponseCreator {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private Validator validator;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> loginAndGetToken(@RequestBody RequestUserJson requestUserJson) {

        User user = userService.findOneByUsername(requestUserJson.getUsername());
        if (user == null) {
            return createBadResponse("Wrong email or password!");
        }
        if (!encoder.matches(requestUserJson.getPassword(), user.getPassword())) {
            return createBadResponse("Wrong email or password!");
        }
        String token = securityService.generateToken(requestUserJson.getUsername(), requestUserJson.getPassword());
        return createGoodResponse(token);
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> registerAndGetToken(@RequestBody RequestUserJson requestUserJson) {
        ResponseEntity<ApiResponse<String>> response = validator.getRegistrationErrorResponse(requestUserJson);
        if (response != null) {
            return response;
        } else {

            User user = new User();
            user.setUsername(requestUserJson.getUsername());
            user.setPassword(encoder.encode(requestUserJson.getPassword()));
            userService.save(user);
            String token = securityService.generateToken(requestUserJson.getUsername(), requestUserJson.getPassword());
            return createGoodResponse(token);
        }
    }
}
