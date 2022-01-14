package com.study.chat.common.user;

import java.util.List;

public interface UserManager {
    User createUser(String id);

    User getUser(String id);

    List<User> getUsers();
}
