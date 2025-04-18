package org.example.RPCServer;

import lombok.AllArgsConstructor;
import org.example.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCRPCServer implements RPCServer {
    private final ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server failed!");
        }
    }

    @Override
    public void stop() {

    }
}
