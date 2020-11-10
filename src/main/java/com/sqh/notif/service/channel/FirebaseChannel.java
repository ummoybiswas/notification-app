package com.sqh.notif.service.channel;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.sqh.notif.model.notification.FirebaseNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class FirebaseChannel implements Channel {
    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void notify(com.sqh.notif.model.notification.Notification notification) {
        try {
            FirebaseNotification firebaseNotification = (FirebaseNotification) notification;
            sendNotification(firebaseNotification.getTo(), firebaseNotification.getTitle(), firebaseNotification.getBody(), firebaseNotification.getTopic());
        } catch (Exception e) {
            log.error("unable to send notification using firebase", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private String sendNotification(String to, String title, String body, String topic) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(to)
                .setTopic(topic)
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);
    }

    @Override
    public boolean supports(com.sqh.notif.model.notification.Notification notification) {
        return notification instanceof FirebaseNotification;
    }
}
