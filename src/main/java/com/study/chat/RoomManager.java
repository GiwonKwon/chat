package com.study.chat;

import java.util.List;

public interface RoomManager {
    Room createRoom(User user);
    List<Room> getRooms();
}
