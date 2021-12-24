package com.study.chat;

public interface Room {
    boolean join(User user);
    void exit(User user);

    boolean hasUser(String id);
}
