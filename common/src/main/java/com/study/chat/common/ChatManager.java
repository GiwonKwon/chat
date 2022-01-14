package com.study.chat.common;

import com.study.chat.common.room.Room;
import com.study.chat.common.user.User;

import java.util.List;

public interface ChatManager {
    User login(String id);

    boolean logout(User user);

    User createUser(String id);

    Room createRoom(User user);

    List<Room> getRooms();
}
