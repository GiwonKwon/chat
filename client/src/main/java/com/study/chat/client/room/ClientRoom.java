package com.study.chat.client.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.room.AbstractRoom;

public class ClientRoom extends AbstractRoom {
    @Override protected boolean doSendMessage(Message message) {
        //send to server
        return false;
    }
}
