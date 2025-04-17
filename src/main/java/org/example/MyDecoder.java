package org.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        short messageType = byteBuf.readShort();
        if (messageType != MessageType.RESPONSE.getCode() &&
                messageType != MessageType.REQUEST.getCode()) {
            System.out.println("Unsupported message type: " + messageType);
            return;
        }
        short serializerType = byteBuf.readShort();
        Serializer serializer = Serializer.getSerializerByCode(serializerType);
        if (serializer == null) throw new Exception("Unknown serializer type: " + serializerType);
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object deserialize = serializer.deserialize(bytes, messageType);
        list.add(deserialize);
    }
}
