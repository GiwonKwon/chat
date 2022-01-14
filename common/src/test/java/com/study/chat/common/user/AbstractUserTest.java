package com.study.chat.common.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractUserTest {
    @Test
    public void getIdTest(){
        User user = new AbstractUser("id");
        assertEquals("id", user.getId());
    }
}