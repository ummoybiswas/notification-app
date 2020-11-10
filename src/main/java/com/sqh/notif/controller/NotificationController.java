package com.sqh.notif.controller;

import com.sqh.notif.model.notification.Notification;
import com.sqh.notif.response.ResponseDTO;
import com.sqh.notif.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/notify")
    public ResponseDTO notify(@RequestBody Notification notification) {
        try {
            switch (notification.getTriggerType()) {
                case SYNC:
                    notificationService.notify(notification);
                    break;
                case ASYNC:
                    notificationService.asyncNotify(notification);
                    break;
                case PERIODIC:
                    notificationService.periodicNotify(notification);
                    break;
                default:
                    throw new RuntimeException("No trigger type found");
            }
        } catch (Exception e) {
            return ResponseDTO.builder()
                    .success(false)
                    .message("Unable to send notification")
                    .build();
        }

        return ResponseDTO.builder()
                .success(true)
                .message("Notification send successfully")
                .build();
    }
}
