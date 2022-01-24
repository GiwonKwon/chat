package com.study.chat.common.bootstrap;

import com.study.chat.common.io.IOHandler;
import com.study.chat.common.io.InboundDataHandler;
import com.study.chat.common.io.Receiver;
import jeus.io.buffer.Buffer;
import jeus.io.buffer.BufferInputStream;
import jeus.io.handler.*;
import jeus.io.impl.nio.handler.NIOStreamHandler;
import jeus.transport.Transport;
import jeus.transport.TransportException;
import jeus.transport.TransportFactory;
import jeus.transport.TransportServer;
import jeus.transport.unification.*;
import jeus.transport.unification.protocol.NetworkProtocol;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Jeus implements Provider{
    private UnifiedTransportFactory factory;
    private UnifiedTransportServer server;
    private Transport transport;


    @Override
    public void initClient() {

    }

    @Override
    public void handler(IOHandler handler) {
        transport = new Transport(handler);
    }

    @Override
    public void initServer() {
        try {
            factory = (UnifiedTransportFactory) TransportFactory.getTransportFactory("unification");
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Override
    public void bind(SocketAddress localAddress) {
        try {
            String address = ((InetSocketAddress)localAddress).getHostName() + ":"
                    + ((InetSocketAddress)localAddress).getPort();
            UnifiedTransportConfig config = (UnifiedTransportConfig) factory.createTransportConfig(address);
            server = (UnifiedTransportServer) factory.bind(config);
            server.start();
            server.register(transport);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void connect(SocketAddress remoteAddress) {

    }

    @Override
    public InetSocketAddress getLocaladdress() {
        if(server != null)
            return server.getAddress();
        return null;
    }

    private static final class Transport implements UnifiedTransportAcceptListener{
        private final IOHandler handler;
        public Transport(IOHandler handler) {
            this.handler = handler;
        }

        private StreamContentReceiver createReceiver(){
            return new StreamContentReceiver() {
                @Override
                public boolean requireSequentialProcess() {
                    return false;
                }

                @Override
                public void receiveContent(Object msg, StreamHandler streamHandler, byte[] bytes) {
                    ((Receiver)handler).receiveMessage(msg);
                }

                @Override
                public void receiveException(Exception e, StreamHandler streamHandler) {

                }
            };
        }

        private StreamContentHandlerCreator createHandler(){
            return new StreamContentHandlerCreator() {
                @Override
                public StreamContentReader createContentReader(StreamHandler streamHandler, int i) throws IOException {
                    return new StreamContentReader() {
                        private final ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                        @Override
                        public boolean hasOwnBuffer() {
                            return true;
                        }

                        @Override
                        public Object parseMessage(Buffer buffer) throws Throwable {
                            if(!streamHandler.isRegistered()) {
                                return null;
                            }
                            byteBuffer.clear();
                            SocketChannel socketChannel = streamHandler.getSocket().getChannel();
                            int read = socketChannel.read(byteBuffer);
                            if(read == 0) {
                                return null;
                            }
                            if(read > 0)
                                streamHandler.access();
                            byteBuffer.flip();
                            switch(((InboundDataHandler)handler).readFromChannel(byteBuffer)){
                                case DONE:
                                    return ParseResult.done(byteBuffer);
                                case UNDERFLOW:
                                    return ParseResult.underflow(1);
                            }
                            throw new IOException();
                        }

                        @Override
                        public void close() {

                        }
                    };
                }

                @Override
                public StreamContentWriter createContentWriter(StreamHandler streamHandler, int i) {
                    return new StreamContentWriter() {
                        @Override
                        public boolean useCustomSerialization(Object o) {
                            return false;
                        }

                        @Override
                        public PacketIterator getBufferAsByte(Object o, byte[] bytes) throws IOException {
                            return null;
                        }

                        @Override
                        public Object[] getBufferToBeWrite(Object o, byte[] bytes) throws IOException {
                            return new Object[0];
                        }

                        @Override
                        public Object[] getBufferToBeWrite(ByteBuffer byteBuffer, byte[] bytes) throws IOException {
                            return new Object[0];
                        }

                        @Override
                        public Object[] getBufferToBeWrite(Buffer buffer, byte[] bytes) throws IOException {
                            return new Object[0];
                        }

                        @Override
                        public Object[] getBufferToBeWrite(byte[] bytes, int i, int i1, byte[] bytes1) throws IOException {
                            return new Object[0];
                        }

                        @Override
                        public ObjectOutput makeObjectOutput(OutputStream outputStream) throws IOException {
                            return null;
                        }

                        @Override
                        public ObjectInput makeObjectInput(InputStream inputStream) throws IOException {
                            return null;
                        }
                    };
                }

                @Override
                public ByteBuffer createByteBuffer(int i) {
                    return null;
                }

                @Override
                public Buffer createBuffer(int i) {
                    return null;
                }

                @Override
                public TimeoutAction createTimeoutAction() {
                    return null;
                }
            };
        }


        @Override
        public void onAccept(TransportServer transportServer, jeus.transport.Transport transport) {
            try {
                UnifiedTransportServer unifiedServer = (UnifiedTransportServer)transportServer;
                UnifiedTransport unifiedTransport = (UnifiedTransport)transport;

                NIOStreamHandler newHandler = unifiedServer.createIOComponentCreator("test")
                        .createStreamHandler(unifiedTransport.getSocket(), createReceiver(), createHandler());
                newHandler.registerTo(unifiedServer.getSelector());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public NetworkProtocol getProtocol() {
            return CHAT.getInstance();
        }

        @Override
        public String getNameForALPN() {
            return null;
        }

        @Override
        public boolean isSelectable() {
            return false;
        }

        @Override
        public boolean needDirectDispatch() {
            return true;
        }
    }

    private static class CHAT implements NetworkProtocol {
        private static final NetworkProtocol instance = new CHAT();

        public static NetworkProtocol getInstance() {
            return instance;
        }

        public String getName() {
            return "CHAT";
        }

        public boolean isExclusive() {
            return false;
        }

        public Result recognize(Buffer readBuffer) throws TransportException {
            return Result.FAIL;
        }
    }
}
