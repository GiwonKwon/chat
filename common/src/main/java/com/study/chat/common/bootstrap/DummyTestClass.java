package com.study.chat.common.bootstrap;

import com.study.chat.common.io.AbstractIOHandler;

import java.net.InetSocketAddress;

/**
 * To be Removed
 */
public class DummyTestClass {
    public static void main(String[] args){
        ChatServerBootstrap serverBootstrap = new ChatServerBootstrap();
        serverBootstrap.provider(Netty.class)
                .localAddress(new InetSocketAddress("localhost", 0))
                .handler(new AbstractIOHandler() {})
                .bind();

        while (true){

        }
    }
}
