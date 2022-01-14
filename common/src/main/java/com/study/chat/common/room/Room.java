package com.study.chat.common.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.message.MessageListener;
import com.study.chat.common.user.User;

import java.util.List;

public interface Room {
    boolean join(User user);

    boolean exit(User user);

    boolean sendMessage(Message message);

    boolean hasUser(String id);

    List<User> getUsers();

    List<Message> getMessages();

    void registerMessageListener(MessageListener messageListener);
}
