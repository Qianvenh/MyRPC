package org.example.old.version2;

import org.example.RPCRequest;
import org.example.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try(Socket socket = new Socket(host, port)) {
            // Client and Server 不能同时是 先InputStream再OutputStream的顺序
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println(request);
            oos.writeObject(request);
            oos.flush();
            return (RPCResponse) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Request send failed");
            return null;
        }
    }
}
