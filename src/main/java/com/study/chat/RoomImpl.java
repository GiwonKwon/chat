package com.study.chat;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RoomImpl implements Room{
    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    @Override public boolean join(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override public void exit(User user) {
        users.remove(user.getId());
    }

    @Override public boolean hasUser(String id) {
        return users.containsKey(id);
    }
}
