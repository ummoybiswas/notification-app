package com.sqh.notif.service.channel;

import com.sqh.notif.model.notification.Notification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class ChannelFactory {
    private final List<Channel> channels;

    public Channel get(Notification notification) {
        return channels.stream()
                .filter(channel -> channel.supports(notification))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No channel found for type " + notification.getChannelType().getName()));
    }
}
