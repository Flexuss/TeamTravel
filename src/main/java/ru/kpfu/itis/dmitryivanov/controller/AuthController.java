package ru.kpfu.itis.dmitryivanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.*;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserLoginJson;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserRegistrationJson;
import ru.kpfu.itis.dmitryivanov.response.ApiResponse;
import ru.kpfu.itis.dmitryivanov.response.AuthorizationResponse;
import ru.kpfu.itis.dmitryivanov.response.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.response.UserInfoResponse;
import ru.kpfu.itis.dmitryivanov.service.DeviceService;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

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
    @Autowired
    private DeviceService deviceService;


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
        deviceService.setDeviceToUser(user, requestUserJson.getDeviceKey());
        return createGoodResponse(new AuthorizationResponse(token,new UserInfoResponse(user)));
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> registerAndGetToken(@RequestBody RequestUserRegistrationJson requestUserRegistrationJson) {
        ResponseEntity<ApiResponse<String>> response = validator.getRegistrationErrorResponse(requestUserRegistrationJson);
        if (response != null) {
            return response;
        } else {
            User user = new User(requestUserRegistrationJson);
            userService.save(user);
            String token = securityService.generateToken(requestUserRegistrationJson.getUsername(), requestUserRegistrationJson.getPassword());
            return createGoodResponse(token);
        }
    }
}
