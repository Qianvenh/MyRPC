package org.example.RPCClient;

import lombok.AllArgsConstructor;
import org.example.RPCRequest;
import org.example.RPCResponse;
import org.example.ServiceRegister;
import org.example.ZkServiceRegister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SimpleClient implements RPCClient {
    private final ServiceRegister serviceRegister;

    public SimpleClient() {
        this.serviceRegister = new ZkServiceRegister();
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        InetSocketAddress address = serviceRegister.serviceDiscovery(request.getInterfaceName());
        String host = address.getHostName();
        int port = address.getPort();
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Sending request: " + request);
            oos.writeObject(request);
            oos.flush();
            return (RPCResponse) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
