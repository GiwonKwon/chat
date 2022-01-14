package com.study.chat.common.message;

public abstract class ControlMessage extends Message{
    public ControlMessage(long timestamp, String userId) {
        super(timestamp, userId);
    }
}
