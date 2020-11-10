package com.sqh.notif.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChannelType {
    SMS(1, "sms"),
    EMAIL(2, "email"),
    FIREBASE(3, "firebase");

    private final int id;
    private final String name;

    public boolean isSms() {
        return this == SMS;
    }

    public boolean isEmail() {
        return this == EMAIL;
    }

    public boolean isFirebase() {
        return this == FIREBASE;
    }
}
