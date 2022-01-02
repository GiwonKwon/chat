package com.study.chat;

import java.util.List;

public abstract class ChatManagerImpl implements ChatManager {
    private final RoomManager roomManager;
    private final UserManager userManager;

    public ChatManagerImpl(RoomManager roomManager, UserManager userManager) {
        this.roomManager = roomManager;
        this.userManager = userManager;
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
