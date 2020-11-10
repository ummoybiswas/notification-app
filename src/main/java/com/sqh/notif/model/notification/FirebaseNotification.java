package com.sqh.notif.model.notification;

import com.sqh.notif.model.ChannelType;
import com.sqh.notif.model.TriggerType;
import lombok.Getter;

@Getter
public class FirebaseNotification extends Notification {
    private final String topic;

    public FirebaseNotification(String to, String title, String body, TriggerType triggerType, String cronExpression, String topic) {
        super(to, title, body, triggerType, cronExpression);
        this.topic = topic;
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.FIREBASE;
    }
}
