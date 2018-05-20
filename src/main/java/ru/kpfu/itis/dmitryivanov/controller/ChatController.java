package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.Message;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.requests.RequestMessageJson;
import ru.kpfu.itis.dmitryivanov.response.*;
import ru.kpfu.itis.dmitryivanov.service.ChatService;
import ru.kpfu.itis.dmitryivanov.service.MessageService;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ChatController extends ResponseCreator {

    @Autowired
    MessageService messageService;

    @Autowired
    ChatService chatService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/message/send", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> sendMessage(@RequestBody RequestMessageJson requestNewTripJson){
        User currentUser = securityService.getCurrentUser();
        Chat currentChat = chatService.getChatById(requestNewTripJson.getChatId());
        Message message = new Message();
        message.setChat(currentChat);
        message.setMessageDate(new Date());
        message.setMessageText(requestNewTripJson.getMessageText());
        message.setSender(currentUser);
        messageService.save(message);
        return createGoodResponse();
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/message/get", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<ChatResponse>> getPrivateMessages(@RequestParam(value = "userId") Long userId){
        User currentUser = securityService.getCurrentUser();
        User user = userService.findOneById(userId);
        Chat chat = chatService.getChatByUsers(currentUser, user);
        List<Message> messages = messageService.getAllMessagesByChat(chat);
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setChatId(chat.getId());
        List<UserResponse> users = new ArrayList<>();
        for(User user1:chat.getChatUsers()){
            users.add(new UserResponse(user1));
        }
        chatResponse.setUsers(users);
        List<MessageResponse> messageResponses = new ArrayList<>();
        for(Message message:messages){
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessageDate(message.getMessageDate());
            messageResponse.setMessageText(message.getMessageText());
            messageResponse.setSenderFio(message.getSender().getFio());
            messageResponses.add(messageResponse);
        }
        chatResponse.setMessages(messageResponses);
        return createGoodResponse(chatResponse);
    }
}
