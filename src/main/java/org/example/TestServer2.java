package org.example;

import org.example.BlogService.BlogService;
import org.example.BlogService.BlogServiceImpl;
import org.example.NettyRPCServer.NettyRPCServer;
import org.example.RPCServer.RPCServer;
import org.example.UserService.UserService;
import org.example.UserService.UserServiceImpl;

public class TestServer2 {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 8900);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(serviceProvider.getPort());
    }
}
