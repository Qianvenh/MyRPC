package org.example.NettyRPCServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;
import org.example.Serializer.JsonSerializer;
import org.example.Serializer.MyDecoder;
import org.example.Serializer.MyEncoder;
import org.example.ServiceProvider;

@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast(new LengthFieldPrepender(4));
//        pipeline.addLast(new ObjectEncoder());
//        pipeline.addLast(new ObjectDecoder(s -> Class.forName(s)));
        pipeline.addLast(new MyDecoder());
//        pipeline.addLast(new MyEncoder(new ObjectSerializer()));
        pipeline.addLast(new MyEncoder(new JsonSerializer()));
        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }
}
