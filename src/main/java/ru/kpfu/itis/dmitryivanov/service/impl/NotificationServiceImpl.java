package ru.kpfu.itis.dmitryivanov.service.impl;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Chat;
import ru.kpfu.itis.dmitryivanov.model.Device;
import ru.kpfu.itis.dmitryivanov.model.Message;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.repository.UserRepository;
import ru.kpfu.itis.dmitryivanov.service.NotificationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    UserRepository userRepository;

    public final static String AUTH_KEY_FCM = "AAAACjsyp-Y:APA91bHiEeXTkg2Mj6rltTuSbvEOQTIo8q1kvMPc_HRgO-hUoIBvUZm717M4iLgOvImU4guJNnMDxZ41nhRGUIMEgwijnNbF_WAK4amk1XHFhhWetZrMdMtQdHcFIZ7izH42s6iwDEvo";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";


    @Override
    public void sendPushNotification(Long userId, Chat chat, Message message) throws IOException {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;
        User user = userRepository.findOne(userId);
        URL url = new URL(FMCurl);

        for(Device device: user.getDevices()) {
            if (device != null && device.getDeviceKey() != null) {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=" + authKey);
                conn.setRequestProperty("Content-Type", "application/json");
                JSONObject data = new JSONObject();
                data.put("to", device.getDeviceKey().trim());
                JSONObject info = new JSONObject();
                info.put("type", 1);
                info.put("chatId", message.getChat().getId());
                info.put("senderFio", message.getSender().getFio());
                info.put("senderId", message.getSender().getId());
                info.put("chatName", message.getChat().getChatName()); // Notification title
                info.put("messageText", message.getMessageText()); // Notification body
                info.put("messageDate", message.getMessageDate());
                info.put("receiverId", userId);
                info.put("messageId", message.getId());
                data.put("data", info);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data.toString());
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        }
    }
}
