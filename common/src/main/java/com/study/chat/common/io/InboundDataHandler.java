package com.study.chat.common.io;

import java.nio.ByteBuffer;

public interface InboundDataHandler extends IOHandler{
    ReadState readFromChannel(ByteBuffer readBuffer);
}
