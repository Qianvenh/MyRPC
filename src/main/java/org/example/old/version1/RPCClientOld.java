package org.example.old.version1;

import org.example.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClientOld {
    public static void main(String[] args) {
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
    }
}
