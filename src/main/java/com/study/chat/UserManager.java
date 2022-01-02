package com.study.chat;

import java.util.List;

public interface UserManager {
    User createUser(String id);



    List<User> getUsers();
}
