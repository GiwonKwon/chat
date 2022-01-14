package com.study.chat.common.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.message.MessageListener;
import com.study.chat.common.user.AbstractUser;
import com.study.chat.common.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class AbstractRoom implements Room{
    protected final ConcurrentMap<String, AbstractUser> users = new ConcurrentHashMap<>();
    private final List<Message> messages = new CopyOnWriteArrayList<>();
    private MessageListener messageListener = (m) -> {};

    @Override public boolean join(User user) {
        return users.putIfAbsent(user.getId(), (AbstractUser) user) == null;
    }

    @Override public boolean exit(User user) {
        return users.remove(user.getId()) != null;
    }

    @Override public boolean hasUser(String id) {
        return users.containsKey(id);
    }

    @Override public boolean sendMessage(Message message) {
        boolean success = doSendMessage(message);
        if(success){
            messages.add(message);
        }
        return success;
    }

    abstract protected boolean doSendMessage(Message message);

    @Override public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    public void onMessage(Message message){
        messages.add(message);
        messageListener.onMessage(message);
    }

    @Override public void registerMessageListener(MessageListener messageListener) {
        this.messageListener = Objects.requireNonNull(messageListener);
    }
}
