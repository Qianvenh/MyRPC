package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);

        UserService userService = clientProxy.getProxy(UserService.class);
        User userByUserId = userService.getUserByUserId(10);
        System.out.println("User id from server: " + userByUserId);
        User user = User.builder().userName("Tom").id(100).sex(true).build();
        Integer res = userService.insertUserId(user);
        System.out.println("Inserted " + res + " user(s)");

        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogByBlogId = blogService.getBlogById(10000);
        System.out.println("Blog id from server: " + blogByBlogId);
    }
}
