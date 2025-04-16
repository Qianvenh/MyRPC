package org.example.old.version1;

import org.example.RPCRequest;
import org.example.RPCResponse;
import org.example.UserService.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServerOld {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try(ServerSocket serverSocket = new ServerSocket(8899)) {
            System.out.println("Server started");
            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        /* 版本1
                        Integer id = ois.readInt();
                        User user4Resp = userService.getUserByUserId(id);
                        oos.writeObject(user4Resp);
                        */
                        RPCRequest request = (RPCRequest) ois.readObject();
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoked = method.invoke(userService, request.getParams());
                        oos.writeObject(RPCResponse.success(invoked));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                             InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("Responsive Exception");
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server failed");
        }
    }
}
