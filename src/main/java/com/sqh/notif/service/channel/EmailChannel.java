package com.sqh.notif.service.channel;

import com.sqh.notif.model.notification.EmailNotification;
import com.sqh.notif.model.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailChannel implements Channel {
    private final JavaMailSender emailSender;
    @Value("${mail.default.send.from}")
    private String emailSendFrom;
    @Value("${mail.default.reply.to}")
    private String emailReplyTo;

    @Override
    public void notify(Notification notification) {
        try {
            sendEmail(notification.getTo(), emailSendFrom, emailReplyTo, notification.getTitle(), notification.getBody());
        } catch (Exception e) {
            log.error("unable to send notification using email", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private void sendEmail(String to, String from, @Nullable String replyTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        if (StringUtils.isNotEmpty(replyTo)) {
            message.setReplyTo(replyTo);
        }
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public boolean supports(Notification notification) {
        return notification instanceof EmailNotification;
    }
}
