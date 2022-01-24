package com.study.chat.common.bootstrap;

import com.study.chat.common.io.IOHandler;
import com.study.chat.common.io.InboundDataHandler;
import com.study.chat.common.io.OutboundDataHandler;
import com.study.chat.common.io.Receiver;
import com.study.chat.common.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Netty implements Provider{
    private ServerBootstrap serverBootstrap;
    private Bootstrap bootstrap;
    private EventLoopGroup group;

    @Override
    public InetSocketAddress getLocaladdress() {
        if (serverBootstrap != null)
            return (InetSocketAddress) serverBootstrap.config().localAddress();

        if (bootstrap != null) {
            return (InetSocketAddress) bootstrap.config().localAddress();
        }

        return null;
    }

    @Override
    public void connect(SocketAddress remoteAddress) {
        bootstrap.remoteAddress(remoteAddress).connect();
    }

    @Override
    public void initClient() {
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioServerSocketChannel.class);
    }

    @Override
    public void handler(IOHandler handler) {
        ChannelHandler reader = new SimpleChannelInboundHandler<ByteBuffer>(){
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuffer byteBuffer) throws Exception {
                ((InboundDataHandler)handler).readFromChannel(byteBuffer);
            }
        };
        ChannelHandler writer = new ChannelOutboundHandlerAdapter(){
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                ((OutboundDataHandler)handler).writeToChannel((ByteBuffer) msg);
            }
        };
        ChannelHandler receiver = new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ((Receiver)handler).receiveMessage(msg);
            }
        };

        if(serverBootstrap != null) {
            serverBootstrap.childHandler(reader)
                    .childHandler(writer)
                    .childHandler(receiver);
        }
        else if(bootstrap != null)
            bootstrap.handler(reader)
                .handler(writer)
                .handler(receiver);
        else
            throw new IllegalStateException("No any initialized bootstrap");
    }

    @Override
    public void initServer() {
        group = new NioEventLoopGroup();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class);
    }

    @Override
    public void bind(SocketAddress localAddress) {
        try {
            serverBootstrap.localAddress(localAddress);
            serverBootstrap.bind().sync();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
