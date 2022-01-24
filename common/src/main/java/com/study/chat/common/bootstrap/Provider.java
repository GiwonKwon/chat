package com.study.chat.common.bootstrap;

import com.study.chat.common.io.IOHandler;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public interface Provider {
    /**
     * for a server-side
     */
    void initServer();

    /**
     * for a client-side
     */
    void initClient();

    /**
     * for the both-side
     * @param handler
     */
    void handler(IOHandler handler);

    /**
     * for a server-side
     */
    void bind(SocketAddress localAddress);

    /**
     * for a client-side
     * @param remoteAddress
     */
    void connect(SocketAddress remoteAddress);

    InetSocketAddress getLocaladdress();
}
