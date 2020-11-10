package com.sqh.notif.model.notification;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sqh.notif.model.ChannelType;
import com.sqh.notif.model.TriggerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailNotification.class, name = "email"),
        @JsonSubTypes.Type(value = FirebaseNotification.class, name = "firebase"),
        @JsonSubTypes.Type(value = SmsNotification.class, name = "sms")
})
public abstract class Notification {
    @Setter
    private Integer id;
    private final String to;
    private final String title;
    private final String body;
    private final TriggerType triggerType;
    private final String cronExpression;

    public abstract ChannelType getChannelType();

    public Notification(String to, String title, String body, TriggerType triggerType, String cronExpression) {
        this.to = to;
        this.title = title;
        this.body = body;
        this.triggerType = triggerType;
        this.cronExpression = cronExpression;
    }
}
