package com.study.chat;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void test(){
        User user = new UserImpl("id");
        assertEquals("id", user.getId());
    }
}