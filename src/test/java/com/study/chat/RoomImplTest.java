package com.study.chat;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomImplTest {
    @Test
    public void joinTest(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        assertTrue(room.join(user));
    }

    @Test
    public void hasUserTest(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        room.join(user);

        assertTrue(room.hasUser(user.getId()));
    }

    @Test
    public void hasUserTest2(){
        Room room = new RoomImpl();
        assertFalse(room.hasUser("non-existed"));
    }

    @Test
    public void hasUserTest3(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        room.join(user);
        room.exit(user);

        assertFalse(room.hasUser(user.getId()));
    }
}