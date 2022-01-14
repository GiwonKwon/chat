package com.study.chat.common.room;

import com.study.chat.common.user.User;

import java.util.List;

public interface RoomManager {
    Room createRoom(User user);

    List<Room> getRooms();
}
