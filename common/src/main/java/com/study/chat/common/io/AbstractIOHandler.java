package com.study.chat.common.io;

public abstract class AbstractIOHandler implements IOHandler{
    protected InboundDataHandler reader;
    protected OutboundDataHandler writer;
    protected Receiver receiver;

    public void reader(InboundDataHandler reader) {
        this.reader = reader;
    }

    public void writer(OutboundDataHandler writer) {
        this.writer = writer;
    }

    public void receiver(Receiver receiver) {
        this.receiver = receiver;
    }
}
