package com.sqh.notif.service.impl;

import com.sqh.notif.model.notification.Notification;
import com.sqh.notif.service.NotificationService;
import com.sqh.notif.service.channel.ChannelFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final ChannelFactory channelFactory;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final AtomicInteger notificationId = new AtomicInteger(1);

    @Override
    public Integer notify(Notification notification) {
        return notifyInternal(notification);
    }

    @Override
    @Async
    public void asyncNotify(Notification notification) {
        notifyInternal(notification);
    }

    @Override
    public void periodicNotify(Notification notification) {
        Runnable task = () -> notifyInternal(notification);
        taskScheduler.schedule(task, new CronTrigger(notification.getCronExpression()));
    }

    private Integer notifyInternal(Notification notification) {
        notification.setId(notificationId.getAndIncrement());
        channelFactory.get(notification).notify(notification);
        return notification.getId();
    }
}
