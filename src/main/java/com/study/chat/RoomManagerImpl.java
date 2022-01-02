package com.study.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomManagerImpl implements RoomManager{
    private final List<Room> rooms = new CopyOnWriteArrayList<>();

    @Override public Room createRoom(User user) {
        Room created = new RoomImpl();
        rooms.add(created);
        return created;
    }

    @Override public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
}
