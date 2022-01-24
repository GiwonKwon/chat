package com.study.chat.common.io;

import java.nio.ByteBuffer;

public interface OutboundDataHandler extends IOHandler{
    void writeToChannel(ByteBuffer writeBuffer);
}
