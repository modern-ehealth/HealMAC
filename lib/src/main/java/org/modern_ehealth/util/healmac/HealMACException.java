package org.modern_ehealth.util.healmac;

/**
 * Exception thrown by HealMAC methods when an expectation is violated.
 *
 * @author Dorin Jinks
 * @since 1.0
 */
public class HealMACException extends RuntimeException {
    public HealMACException(String message) {
        super(message);
    }

    public HealMACException(String message, Throwable cause) {
        super(message, cause);
    }
}
