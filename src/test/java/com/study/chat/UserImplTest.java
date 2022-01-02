package com.study.chat;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserImplTest {
    @Test
    public void getIdTest(){
        User user = new UserImpl("id");
        assertEquals("id", user.getId());
    }
}