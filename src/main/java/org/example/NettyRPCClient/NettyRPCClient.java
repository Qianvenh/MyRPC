package org.example.NettyRPCClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.example.RPCClient.RPCClient;
import org.example.RPCRequest;
import org.example.RPCResponse;
import org.example.ServiceRegister;
import org.example.ZkServiceRegister;

import java.net.InetSocketAddress;

public class NettyRPCClient implements RPCClient {
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private final ServiceRegister serviceRegister;

    public NettyRPCClient() {
        this.serviceRegister = new ZkServiceRegister();
    }

    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            System.out.println(request.getInterfaceName());
            InetSocketAddress address = serviceRegister.serviceDiscovery(request.getInterfaceName());
            String host = address.getHostString();
            int port = address.getPort();
            ChannelFuture  channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse rpcResponse = channel.attr(key).get();
            System.out.println(rpcResponse);
            return rpcResponse;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
