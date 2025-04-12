package org.example;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    Map<String, Object> interfaceProvider;

    ServiceProvider() {
        interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getServiceInterface(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
