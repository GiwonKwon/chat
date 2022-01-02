package com.study.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomImpl implements Room{
    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();
    private final List<Message> messages = new CopyOnWriteArrayList<>();

    @Override public boolean join(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override public void exit(User user) {
        users.remove(user.getId());
    }

    @Override public boolean hasUser(String id) {
        return users.containsKey(id);
    }

    @Override public boolean sendMessage(Message message) {
        return messages.add(message);
    }

    @Override public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}
