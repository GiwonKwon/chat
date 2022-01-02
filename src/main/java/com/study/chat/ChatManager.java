package com.study.chat;

import java.util.List;

public interface ChatManager {
    User login(String id);

    boolean logout(User user);

    User createUser(String id);

    Room createRoom(User user);

    List<Room> getRooms();
}
