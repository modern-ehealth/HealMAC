package org.modern_ehealth.util.healmac;

public class HealMACException extends RuntimeException {
    public HealMACException(String message) {
        super(message);
    }

    public HealMACException(String message, Throwable cause) {
        super(message, cause);
    }
}
