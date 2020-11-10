package com.sqh.notif.service.channel;

import com.sqh.notif.model.notification.Notification;
import com.sqh.notif.model.notification.SmsNotification;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SmsChannel implements Channel {
    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.account.auth.token}")
    private String authToken;
    @Value("${twilio.account.sender.number}")
    private String from;

    @Override
    public void notify(Notification notification) {
        try {
            SmsNotification smsNotification = (SmsNotification) notification;
            sendSms(smsNotification.getTo(), smsNotification.getBody());
        } catch (Exception e) {
            log.error("unable to send notification using sms", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private void sendSms(String to, String body) {
        Twilio.init(accountSid, authToken);

        Message message = Message
                .creator(new PhoneNumber(to),
                        new PhoneNumber(from),
                        body)
                .create();

        log.debug("Sms::: {}, status: {}", message.getSid(), message.getStatus());
    }

    @Override
    public boolean supports(Notification notification) {
        return notification instanceof SmsNotification;
    }
}
