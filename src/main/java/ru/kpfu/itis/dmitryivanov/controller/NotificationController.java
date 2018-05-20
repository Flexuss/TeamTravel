package ru.kpfu.itis.dmitryivanov.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dmitryivanov.requests.RequestNotificationJson;
import ru.kpfu.itis.dmitryivanov.response.ApiResponse;
import ru.kpfu.itis.dmitryivanov.response.ResponseCreator;
import ru.kpfu.itis.dmitryivanov.service.NotificationService;

import java.io.IOException;

@RestController
public class NotificationController extends ResponseCreator {

    @Autowired
    NotificationService notificationService;

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> createNewTrip(@RequestBody RequestNotificationJson requestNotificationJson) throws IOException {
        notificationService.sendPushNotification(requestNotificationJson.getUserId(),requestNotificationJson.getTitle(),requestNotificationJson.getText());
        return createGoodResponse();
    }
}
