package com.study.chat;

import java.util.Collections;
import java.util.List;

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
