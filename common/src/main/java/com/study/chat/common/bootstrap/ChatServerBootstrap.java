package com.study.chat.common.bootstrap;

public class ChatServerBootstrap extends AbstractChatBootstrap<ChatServerBootstrap, Provider>{
    public ChatServerBootstrap() {
    }


    public void bind() {
        if (localAddress == null) {
            throw new IllegalStateException("localAddress not set");
        }
        provider = providerFactory.newProvider(); // Jeus, Netty
        provider.initServer();
        provider.handler(handler);
        provider.bind(localAddress);
    }
}
