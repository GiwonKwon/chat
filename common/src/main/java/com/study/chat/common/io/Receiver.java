package com.study.chat.common.io;

import com.study.chat.common.message.Message;

public interface Receiver extends IOHandler {
    void receiveMessage(Object msg);
}
