package org.example;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RPCRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsTypes;
}
