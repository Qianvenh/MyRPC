package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    REQUEST((short) 0),
    RESPONSE((short) 1);

    private final short code;

    public static MessageType valueOf(short code) {
        switch (code) {
            case 0:
                return REQUEST;
            case 1:
                return RESPONSE;
            default:
                throw new IllegalArgumentException("Unknown MessageType code: " + code);
        }
    }
}
