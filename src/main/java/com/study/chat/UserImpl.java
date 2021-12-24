package com.study.chat;

public class UserImpl implements User {
    private final String id;

    public UserImpl(){
        this("TEST");
    }

    public UserImpl(String id) {
        this.id = id;
    }

    @Override public String getId() {
        return id;
    }
}
