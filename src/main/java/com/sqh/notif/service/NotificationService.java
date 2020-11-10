package com.sqh.notif.service;

import com.sqh.notif.model.notification.Notification;

public interface NotificationService {
    Integer notify(Notification notification);

    void asyncNotify(Notification notification);

    void periodicNotify(Notification notification);
}
