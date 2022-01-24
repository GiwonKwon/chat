package com.study.chat.common.bootstrap;

import com.study.chat.common.io.IOHandler;

import java.net.SocketAddress;

public abstract class AbstractChatBootstrap<B extends AbstractChatBootstrap<B, C>, C extends Provider>{
    protected ProviderFactory<? extends C> providerFactory;
    protected SocketAddress localAddress;
    protected Provider provider;
    protected IOHandler handler;

    AbstractChatBootstrap() {
        // not allowed
    }

    public SocketAddress getLocalAddress(){
        return provider.getLocaladdress();
    }

    public B localAddress(SocketAddress localAddress) {
        this.localAddress = localAddress;
        return (B) this;
    }

    public B handler(IOHandler handler) {
        if (handler == null) {
            throw new NullPointerException("handler");
        }
        this.handler = handler;
        return (B) this;
    }

    public B provider(Class<? extends C> providerClass) {
        if (providerClass == null) {
            throw new NullPointerException("providerClass");
        }
        return providerFactory(new BootstrapProviderFactory<>(providerClass));
    }

    @SuppressWarnings("unchecked")
    public B providerFactory(ProviderFactory<? extends C> providerFactory) {
        if (providerFactory == null) {
            throw new NullPointerException("providerFactory");
        }
        if (this.providerFactory != null) {
            throw new IllegalStateException("providerFactory set already");
        }

        this.providerFactory = providerFactory;

        return (B) this;
    }

    private static final class BootstrapProviderFactory<E extends Provider> implements ProviderFactory<E> {
        private final Class<? extends E> clazz;

        BootstrapProviderFactory(Class<? extends E> clazz) {
            this.clazz = clazz;
        }

        @Override
        public E newProvider() {
            try {
                return clazz.newInstance();
            } catch (Throwable t) {
                throw new RuntimeException("Unable to create Provider from class " + clazz, t);
            }
        }

        @Override
        public String toString() {
            return clazz.getSimpleName() + ".class";
        }
    }
}
