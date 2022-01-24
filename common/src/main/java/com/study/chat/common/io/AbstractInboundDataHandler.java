package com.study.chat.common.io;

import java.nio.ByteBuffer;

public abstract class AbstractInboundDataHandler implements InboundDataHandler {
    @Override
    public ReadState readFromChannel(ByteBuffer readBuffer) {
        if(readBuffer != null && readBuffer.hasRemaining())
            return ReadState.DONE;
        else
            return ReadState.UNDERFLOW;
    }
}
