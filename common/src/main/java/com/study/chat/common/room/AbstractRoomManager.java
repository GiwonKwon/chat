package com.study.chat.common.room;

import com.study.chat.common.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractRoomManager implements RoomManager{
    private final List<Room> rooms = new CopyOnWriteArrayList<>();

    @Override public Room createRoom(User user) {
        AbstractRoom created = createRoom();
        created.join(user);
        rooms.add(created);
        return created;
    }

    abstract protected AbstractRoom createRoom();

    @Override public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
}
