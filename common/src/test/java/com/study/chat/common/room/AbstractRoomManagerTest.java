package com.study.chat.common.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.user.AbstractUser;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractRoomManagerTest {

    @Test
    public void createRoomTest() {
        RoomManager roomManager = createRoomManager();

        assertNotNull(roomManager.createRoom(new AbstractUser()));
    }

    @Test
    public void getRoomsTest() {
        RoomManager roomManager = createRoomManager();
        Room room = roomManager.createRoom(new AbstractUser());

        assertTrue(roomManager.getRooms().contains(room));
    }

    @Test
    public void getRoomsTest2() {
        RoomManager roomManager = createRoomManager();

        assertTrue(roomManager.getRooms().isEmpty());
    }

    private AbstractRoomManager createRoomManager(){
        return new AbstractRoomManager() {
            @Override protected AbstractRoom createRoom() {
                return new AbstractRoom() {
                    @Override protected boolean doSendMessage(Message message) {
                        return true;
                    }
                };
            }
        };
    }
}