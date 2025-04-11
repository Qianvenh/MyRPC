package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {
        /* 版本1
        try(Socket socket = new Socket("127.0.0.1", 8899)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            User user = (User) objectInputStream.readObject();
            System.out.println("Server response : " + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Client exception : " + e.getMessage());
        }
        */
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("User id from server: " + userByUserId);
        User user = User.builder().userName("Tom").id(100).sex(true).build();
        Integer res = proxy.insertUserId(user);
        System.out.println("Inserted " + res + " user(s)");
    }
}
