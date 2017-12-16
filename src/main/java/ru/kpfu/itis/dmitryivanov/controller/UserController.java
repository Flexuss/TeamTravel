package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.model.Friend;
import ru.kpfu.itis.dmitryivanov.model.Trip;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.service.FriendService;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 14.12.2017.
 */

@RestController
@RequestMapping(value = "/secure/users")
public class UserController extends ResponseCreator {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    FriendService friendService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<UserInfoResponse>> getUser(@RequestParam(value = "id", required = true) Long id){
        return createGoodResponse(new UserInfoResponse(userService.findOneById(id)));
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<UserInfoResponse>> getMyProfile(){
        return createGoodResponse(new UserInfoResponse(securityService.getCurrentUser()));
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> findUsers(@RequestParam(value = "username", required = true) String username){
        User currentUser = securityService.getCurrentUser();
        List<User> friends = friendService.getFriendsByUser(currentUser);
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:userService.findAllByUsername(username)){
            UserResponse newUser = new UserResponse(user);
            if(friends.contains(user)){
                newUser.setAllreadyFriend(true);
            }
            users.add(newUser);
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user_friends", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> findUserFriends(@RequestParam(value = "id", required = true) Long id){
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:friendService.getFriendsByUser(userService.findOneById(id))){
            users.add(new UserResponse(user));
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/add_friend", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<String>> addFriend(@RequestParam(value = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        User newFriend = userService.findOneById(id);
        Friend friend = new Friend();
        friend.setUser(currentUser);
        friend.setFriend(newFriend);
        friendService.save(friend);
        return createGoodResponse();
    }
}
