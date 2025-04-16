package org.example;

import org.example.BlogService.BlogService;
import org.example.BlogService.BlogServiceImpl;
import org.example.RPCServer.RPCServer;
import org.example.UserService.UserService;
import org.example.UserService.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
//        RPCServer rpcServer = new SimpleRPCRPCServer(serviceProvider);
//        RPCServer rpcServer = new ThreadPoolRPCRPCServer(serviceProvider);
        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(8899);
    }
}
