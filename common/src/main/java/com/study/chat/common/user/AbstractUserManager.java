package com.study.chat.common.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractUserManager implements UserManager{
    private final ConcurrentMap<String, AbstractUser> idToUser = new ConcurrentHashMap<>();

    @Override public User createUser(String id) {
        AbstractUser user = doCreateUser(id);
        if(user != null) {
            idToUser.put(id, user);
        }
        return user;
    }

    protected abstract AbstractUser doCreateUser(String id);

    @Override public User getUser(String id) {
        return idToUser.get(id);
    }

    @Override public List<User> getUsers() {
        return new ArrayList<>(idToUser.values());
    }
}
