package org.example.RPCClient;

import org.example.RPCRequest;
import org.example.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
