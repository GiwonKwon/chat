package com.study.chat.common.user;

public class AbstractUser implements User {
    private final String id;

    public AbstractUser(){
        this("TEST");
    }

    public AbstractUser(String id) {
        this.id = id;
    }

    @Override public String getId() {
        return id;
    }
}
