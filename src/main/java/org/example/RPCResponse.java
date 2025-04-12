package org.example;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("Server Error").build();
    }
}
