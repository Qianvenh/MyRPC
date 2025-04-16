package org.example.RPCServer;

import lombok.AllArgsConstructor;
import org.example.RPCRequest;
import org.example.RPCResponse;
import org.example.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private final Socket socket;
    private final ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            // Client and Server 不能同时是 先InputStream再OutputStream的顺序
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RPCRequest request = (RPCRequest) ois.readObject();
            RPCResponse response = getResponse(request);
            oos.writeObject(response);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("IO error in Server");
        }
    }

    public RPCResponse getResponse(RPCRequest request) {
        String interfaceName = request.getInterfaceName();
        Object service = serviceProvider.getServiceInterface(interfaceName);
        try {
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object data = method.invoke(service, request.getParams());
            return RPCResponse.success(data);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return RPCResponse.fail();
        }
    }
}
