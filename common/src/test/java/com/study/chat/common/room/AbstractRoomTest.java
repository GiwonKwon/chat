package com.study.chat.common.room;

import com.study.chat.common.message.Message;
import com.study.chat.common.message.TextMessage;
import com.study.chat.common.user.User;
import com.study.chat.common.user.AbstractUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class AbstractRoomTest {
    @Test
    public void joinTest(){
        Room room = createRoom();
        User user = new AbstractUser();

        assertTrue(room.join(user));
    }

    @Test
    public void joinTest2(){
        Room room = createRoom();
        User user = new AbstractUser();

        room.join(user);

        assertFalse(room.join(user));
    }

    @Test
    public void exitTest(){
        Room room = createRoom();
        User user = new AbstractUser();

        room.join(user);

        assertTrue(room.exit(user));
    }

    @Test
    public void exitTest2(){
        Room room = createRoom();
        User user = new AbstractUser();

        room.join(user);
        room.exit(user);

        assertFalse(room.exit(user));
    }

    @Test
    public void hasUserTest(){
        Room room = createRoom();
        User user = new AbstractUser();

        room.join(user);

        assertTrue(room.hasUser(user.getId()));
    }

    @Test
    public void hasUserTest2(){
        Room room = createRoom();

        assertFalse(room.hasUser("non-existed"));
    }

    @Test
    public void getUsersTest(){
        Room room = createRoom();

        assertTrue(room.getUsers().isEmpty());
    }

    @Test
    public void getUsersTest2(){
        Room room = createRoom();

        List<User> toBeJoined = new ArrayList<>();
        toBeJoined.add(new AbstractUser("1"));
        toBeJoined.add(new AbstractUser("2"));
        toBeJoined.add(new AbstractUser("3"));

        toBeJoined.forEach(room::join);

        List<User> joined = room.getUsers();

        toBeJoined.sort(Comparator.comparing(User::getId));
        joined.sort(Comparator.comparing(User::getId));


        assertEquals(toBeJoined, joined);
    }

    @Test
    public void messageListenerTest(){
        AtomicReference<Message> messageReference = new AtomicReference<>();
        AbstractRoom room = createRoom();
        room.registerMessageListener(messageReference::set);

        TextMessage message = new TextMessage(0, "test", "test");

        room.onMessage(message);

        assertEquals(message.getContents(), ((TextMessage)messageReference.get()).getContents());
    }

    @Test
    public void messageListenerTest2(){
        AtomicReference<Message> messageReference = new AtomicReference<>();
        AbstractRoom room = createRoom();
        room.registerMessageListener(messageReference::set);

        TextMessage message = new TextMessage(0, "test", "test");

        room.onMessage(message);

        assertEquals(Collections.singletonList(message), room.getMessages());
    }
    
    private AbstractRoom createRoom(){
        return new AbstractRoom() {
            @Override protected boolean doSendMessage(Message message) {
                return true;
            }
        };
    }
}