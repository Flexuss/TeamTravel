package ru.kpfu.itis.dmitryivanov.service.impl;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.Device;
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

    public final static String AUTH_KEY_FCM = "AAAATMSyFKo:APA91bEZP9NCCqdV8_7I3R55xRLqLnQ0B4nbT7Yx6lxVOaBbzYOV0vh_YIM7bMQbxxxPS7clJvHjBQPRh_LBZKW5e6o0bN1LizHY9y7yPybB_oO19yeyh7tpXN8utxl8BQXVd8uN6R-7";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";


    @Override
    public void sendPushNotification(Long userId, String notificationTitle, String notificationInfo) throws IOException {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;
        User user = userRepository.findOne(userId);
        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        for(Device device: user.getDevices()) {
            JSONObject data = new JSONObject();
            data.put("to", device.getDeviceKey().trim());
            JSONObject info = new JSONObject();
            info.put("title", notificationTitle); // Notification title
            info.put("body", notificationInfo); // Notification body
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
