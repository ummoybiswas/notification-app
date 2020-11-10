package com.sqh.notif.service.channel;

import com.sqh.notif.model.notification.Notification;

public interface Channel {
    void notify(Notification notification);
    boolean supports(Notification notification);
}
