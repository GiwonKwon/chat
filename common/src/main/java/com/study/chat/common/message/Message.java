package com.study.chat.common.message;

public class Message {
    private final long timestamp;
    private final String userId;

    public Message(long timestamp, String userId) {
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }
}
