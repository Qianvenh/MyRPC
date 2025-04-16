package org.example.old.version2;

import org.example.BlogService.Blog;
import org.example.BlogService.BlogService;
import org.example.UserService.User;
import org.example.UserService.UserService;

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
