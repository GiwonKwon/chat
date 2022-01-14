package com.study.chat.common.message;

public class TextMessage extends DataMessage<String> {
    public TextMessage(long timestamp, String userId, String contents) {
        super(timestamp, userId, contents);
    }
}
