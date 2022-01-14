package com.study.chat.common;

import com.study.chat.common.room.Room;
import com.study.chat.common.room.RoomManager;
import com.study.chat.common.user.User;
import com.study.chat.common.user.UserManager;

import java.util.List;

public abstract class AbstractChatManager implements ChatManager {
    private final RoomManager roomManager;
    private final UserManager userManager;

    public AbstractChatManager(RoomManager roomManager, UserManager userManager) {
        this.roomManager = roomManager;
        this.userManager = userManager;
    }

    @Override public User login(String id) {
        return userManager.getUser(id);
    }

    @Override public User createUser(String id) {
        return userManager.createUser(id);
    }

    @Override
    public Room createRoom(User user) {
        return roomManager.createRoom(user);
    }

    @Override public List<Room> getRooms() {
        return roomManager.getRooms();
    }
}
