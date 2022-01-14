package com.study.chat.server.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.room.AbstractRoom;

public class ServerRoom extends AbstractRoom {
    @Override protected boolean doSendMessage(Message message) {
        //send to all users.
        return false;
    }
}
