package com.study.chat;

import java.util.List;

public interface Room {
    boolean join(User user);

    void exit(User user);

    boolean sendMessage(Message message);

    boolean hasUser(String id);

    List<User> getUsers();
}
