package org.example;

import lombok.Data;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@Data
public class ServiceProvider {
    private final Map<String, Object> interfaceProvider;
    private final ServiceRegister register;
    private final String host;
    private final int port;

    ServiceProvider(String host, int port) {
        this.interfaceProvider = new HashMap<>();
        this.register = new ZkServiceRegister();
        this.host = host;
        this.port = port;
    }

    public void provideServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            interfaceProvider.put(clazz.getName(), service);
            register.register(clazz.getName(), new InetSocketAddress(host, port));
        }
    }

    public Object getServiceInterface(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
