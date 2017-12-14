package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.response.ApiResponse;
import ru.kpfu.itis.dmitryivanov.response.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.response.UserInfoResponse;
import ru.kpfu.itis.dmitryivanov.response.UserResponse;
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
    private ResponseEntity<ApiResponse<UserInfoResponse>> getUser(@RequestParam(value = "id", required = true) Long id){
        return createGoodResponse(new UserInfoResponse());
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<UserInfoResponse>> getMyProfile(){
        return createGoodResponse(new UserInfoResponse());
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> findUsers(@RequestParam(value = "username", required = true) String username){
        ArrayList<UserResponse> users = new ArrayList<>();
        for(int i=0;i<5;i++){
            users.add(new UserResponse());
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user_friends", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> findUserFriends(@RequestParam(value = "id", required = true) Long id){
        ArrayList<UserResponse> users = new ArrayList<>();
        for(int i=0;i<5;i++){
            users.add(new UserResponse());
        }
        return createGoodResponse(users);
    }
}
