package com.study.chat.common.bootstrap;

import com.study.chat.common.io.AbstractIOHandler;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class ChatServerBootstrapTest extends TestCase {
    @Test
    public void testServerStart() throws IOException, InterruptedException {
        /*
        ChatServerBootstrap serverBootstrap = new ChatServerBootstrap();
        serverBootstrap.provider(Netty.class)
                .localAddress(new InetSocketAddress("localhost", 0))
                .handler(new AbstractIOHandler() {})
                .bind();
        Thread.sleep(500);
        if(serverBootstrap.getLocalAddress() != null) {
            System.out.println(serverBootstrap.getLocalAddress());
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(true);
            channel.connect(serverBootstrap.getLocalAddress());
            assertTrue(channel.isConnected());
        } else
            assert false;
         */
    }
}