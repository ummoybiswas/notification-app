package com.sqh.notif.model.notification;

import com.sqh.notif.model.ChannelType;
import com.sqh.notif.model.TriggerType;
import lombok.Getter;

@Getter
public class SmsNotification extends Notification {
    public SmsNotification(String to, String title, String body, TriggerType triggerType, String cronExpression) {
        super(to, title, body, triggerType, cronExpression);
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.SMS;
    }
}
