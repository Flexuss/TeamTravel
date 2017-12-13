package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.ApiResponse;
import ru.kpfu.itis.dmitryivanov.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.requests.RequestUserJson;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.ArrayList;

/**
 * Created by Dmitry on 14.12.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController extends ResponseCreator {

    @Autowired
    UserService userService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<User>> getUser(@RequestParam(value = "username", required = true) String username){
        User user = userService.findOneByUsername(username);
        return createGoodResponse(user);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<User>>> findUsers(@RequestParam(value = "username", required = true) RequestUserJson requestUserJson){
        ArrayList<User> users = userService.findAllByUsername(requestUserJson.getUsername());
        return createGoodResponse(users);
    }
}
