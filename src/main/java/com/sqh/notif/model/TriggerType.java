package com.sqh.notif.model;

import lombok.Getter;

@Getter
public enum TriggerType {
    SYNC, ASYNC, PERIODIC;

    public boolean isSync() {
        return this == SYNC;
    }

    public boolean isAsync() {
        return this == ASYNC;
    }

    public boolean isPeriodic() {
        return this == PERIODIC;
    }
}
