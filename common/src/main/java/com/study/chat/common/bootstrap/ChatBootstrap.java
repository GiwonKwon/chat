package com.study.chat.common.bootstrap;

import java.net.SocketAddress;

public class ChatBootstrap extends AbstractChatBootstrap<ChatBootstrap, Provider>{
    private SocketAddress remoteAddress;

    public ChatBootstrap() {
    }

    public ChatBootstrap remoteAddress(SocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }

    public void connect() {
        if (remoteAddress == null) {
            throw new IllegalStateException("remoteAddress not set");
        }
        provider = providerFactory.newProvider(); // Jeus, Netty
        provider.initClient();
        provider.handler(handler);
        provider.connect(remoteAddress);
    }
}
