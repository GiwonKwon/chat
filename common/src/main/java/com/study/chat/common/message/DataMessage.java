package com.study.chat.common.message;

public abstract class DataMessage<T> extends Message{
    private final T contents;

    public DataMessage(long timestamp, String userId, T contents) {
        super(timestamp, userId);
        this.contents = contents;
    }

    public T getContents() {
        return contents;
    }
}
