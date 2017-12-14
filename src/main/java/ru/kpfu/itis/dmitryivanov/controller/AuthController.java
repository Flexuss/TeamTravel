package ru.kpfu.itis.dmitryivanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dmitryivanov.*;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserLoginJson;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserRegistrationJson;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.HashMap;

/**
 * Created by Dmitry on 06.11.2017.
 */

@RestController
public class AuthController extends ResponseCreator {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private Validator validator;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<AuthorizationResponse>> loginAndGetToken(@RequestBody RequestUserLoginJson requestUserJson) {

        User user = userService.findOneByUsername(requestUserJson.getUsername());
        if (user == null) {
            return createBadResponse("Wrong username or password!");
        }
        if (!encoder.matches(requestUserJson.getPassword(), user.getPassword())) {
            return createBadResponse("Wrong username or password!");
        }
        String token = securityService.generateToken(requestUserJson.getUsername(), requestUserJson.getPassword());

        return createGoodResponse(new AuthorizationResponse(token,user));
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> registerAndGetToken(@RequestBody RequestUserRegistrationJson requestUserRegistrationJson) {
        ResponseEntity<ApiResponse<String>> response = validator.getRegistrationErrorResponse(requestUserRegistrationJson);
        if (response != null) {
            return response;
        } else {

            User user = new User();
            user.setUsername(requestUserRegistrationJson.getUsername());
            user.setPassword(encoder.encode(requestUserRegistrationJson.getPassword()));
            userService.save(user);
            String token = securityService.generateToken(requestUserRegistrationJson.getUsername(), requestUserRegistrationJson.getPassword());
            return createGoodResponse(token);
        }
    }
}
