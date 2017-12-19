package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.dmitryivanov.Validator;
import ru.kpfu.itis.dmitryivanov.model.*;
import ru.kpfu.itis.dmitryivanov.requests.ChangeUserRequestJson;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    PhotoService photoService;

    @Autowired
    InviteService inviteService;

    @Autowired
    Validator validator;

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
            if(!user.equals(currentUser)) {
                users.add(newUser);
            }
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/user_friends", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> findUserFriends(@RequestParam(value = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        List<User> friends = friendService.getFriendsByUser(currentUser);
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:friendService.getFriendsByUser(userService.findOneById(id))){
            UserResponse newUser = new UserResponse(user);
            if(friends.contains(user)){
                newUser.setAllreadyFriend(true);
            }
            if(!user.equals(currentUser)) {
                users.add(newUser);
            }
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/my_friends", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<ArrayList<UserResponse>>> myFriends(){
        ArrayList<UserResponse> users = new ArrayList<>();
        for(User user:friendService.getFriendsByUser(securityService.getCurrentUser())){
            users.add(new UserResponse(user));
        }
        return createGoodResponse(users);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/add_friend", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<String>> addFriend(@RequestParam(value = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        User newFriend = userService.findOneById(id);
        if(friendService.getFriendsByUserAndFriend(currentUser,newFriend)==null) {
            Friend friend = new Friend();
            friend.setUser(currentUser);
            friend.setFriend(newFriend);
            friendService.save(friend);
            return createGoodResponse("Friend save success");
        }
        return createBadResponse("This user is already your friend");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/remove_friend", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<String>> removeFriend(@RequestParam(value = "id", required = true) Long id){
        User currentUser = securityService.getCurrentUser();
        User newFriend = userService.findOneById(id);
        Friend friend = friendService.getFriendsByUserAndFriend(currentUser,newFriend);
        if(friend!=null) {
            friendService.remove(friend);
            return createGoodResponse("Remove Success");
        }
        return createBadResponse("This user is not your friend");
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/change_avatar", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<Long>> changeAvatar(@RequestPart("image")MultipartFile image){
        User currentUser = securityService.getCurrentUser();
        if(!image.isEmpty()){
            try {
                String name = currentUser.getUsername().replaceAll(".","")+ UUID.randomUUID().toString();
                byte[] bytes = image.getBytes();
                File file = new File(System.getProperty("user.dir")+File.separator+name);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                String path = file.getAbsolutePath();
                Photo photo = new Photo();
                photo.setUser(currentUser);
                photo.setPath(path);
                photoService.save(photo);
                currentUser.setImage(photo);
                userService.save(currentUser);
                return createGoodResponse(currentUser.getImage().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/get_image", method = RequestMethod.GET)
    private void getAvatar(@RequestParam("id") Long id, HttpServletResponse httpServletResponse){
        Photo photo = photoService.getOneById(id);
        if(photo==null){
            httpServletResponse.setStatus(404);
            return;
        }
        try {
            byte[] response = Files.readAllBytes(Paths.get(photo.getPath()));
            httpServletResponse.getOutputStream().write(response);
            httpServletResponse.setStatus(200);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpServletResponse.setStatus(404);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/change_profile", method = RequestMethod.POST)
    private ResponseEntity<ApiResponse<UserInfoResponse>> changeProfile(@RequestBody ChangeUserRequestJson changeUserRequestJson){
        User currentUser = securityService.getCurrentUser();
        currentUser.setFio(changeUserRequestJson.getFio());
        if(validator.isUsernameValid(changeUserRequestJson.getUsername())) {
            currentUser.setUsername(changeUserRequestJson.getUsername());
        }else return createBadResponse("Invalid username");
        currentUser.setPhoneNumber(changeUserRequestJson.getPhoneNumber());
        if(validator.isEmailValid(changeUserRequestJson.getEmail())) {
            currentUser.setEmail(changeUserRequestJson.getEmail());
        }else return createBadResponse("Invalid email");
        if(validator.isPasswordCorrect(changeUserRequestJson.getPassword())) {
            currentUser.setPassword(changeUserRequestJson.getPassword());
        }else return createBadResponse("Invalid password");
        currentUser.setBirthDate(changeUserRequestJson.getBirthDate());
        currentUser.setCountry(changeUserRequestJson.getCountry());
        currentUser.setInterests(changeUserRequestJson.getCountry());
        userService.save(currentUser);
        UserInfoResponse userInfoResponse = new UserInfoResponse(currentUser);
        return createGoodResponse(userInfoResponse);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/get_invites", method = RequestMethod.GET)
    private ResponseEntity<ApiResponse<List<InviteResponse>>> getInvites(){
        User currentUser = securityService.getCurrentUser();
        List<Invites> invites = inviteService.getAllByUser(currentUser);
        List<InviteResponse> inviteResponses = new ArrayList<>();
        for(Invites invites1:invites){
            inviteResponses.add(new InviteResponse(invites1));
        }
        return createGoodResponse(inviteResponses);
    }


}
