package com.study.chat;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {
    @Test
    public void test(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        assertTrue(room.join(user));
    }

    @Test
    public void test2(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        room.join(user);

        assertTrue(room.hasUser(user.getId()));
    }

    @Test
    public void test3(){
        Room room = new RoomImpl();
        assertFalse(room.hasUser("non-existed"));
    }

    @Test
    public void test4(){
        Room room = new RoomImpl();
        User user = new UserImpl();

        room.join(user);
        room.exit(user);

        assertFalse(room.hasUser(user.getId()));
    }
}