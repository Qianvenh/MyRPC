package org.example;

import org.example.BlogService.Blog;
import org.example.BlogService.BlogService;
import org.example.UserService.User;
import org.example.UserService.UserService;

public class TestClient {
    public static void main(String[] args) {
//        SimpleClient simpleClient = new SimpleClient("127.0.0.1", 8899);
//        RPCClientProxy proxy = new RPCClientProxy(simpleClient);
        NettyRPCClient client = new NettyRPCClient("127.0.0.1", 8899);
        RPCClientProxy proxy = new RPCClientProxy(client);
        UserService userService = proxy.getProxy(UserService.class);
        User user = userService.getUserByUserId(10);

        BlogService blogService = proxy.getProxy(BlogService.class);
        Blog blog = blogService.getBlogById(1000);
    }
}
