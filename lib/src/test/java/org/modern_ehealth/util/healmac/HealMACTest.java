package org.modern_ehealth.util.healmac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HealMACTest {

    // generateCode

    @Test
    void testGenerateCode_calculatesMurmur3HMAC() {
        var hmac = HealMAC.generateCode(TEST_KEY, TEST_MESSAGE);

        // Calculated from python mmh3
        var expected = 1323342304;

        assertEquals(expected, hmac);
    }

    @Test
    void testGenerateCode_throwsForEmptyKey() {
        var exception = assertThrows(HealMACException.class, () -> {
                HealMAC.generateCode(new byte[]{}, TEST_MESSAGE);
            });

        assertTrue(exception.getMessage().matches("empty key"));
    }

    @Test
    void testGenerateCode_throwsForEmptyMessage() {
        var exception = assertThrows(HealMACException.class, () -> {
                HealMAC.generateCode(TEST_KEY, new byte[]{});
            });

        assertTrue(exception.getMessage().matches("empty message"));
    }


    // validateCode

    @Test
    void testValidateCode_returnsTrueForValidCode() {
        var code = 1323342304;

        var result = HealMAC.validateCode(TEST_KEY, TEST_MESSAGE, code);
        assertTrue(result);
    }

    @Test
    void testValidateCode_returnsFalseForInvalidCode() {
        var code = 1568202873;

        var result = HealMAC.validateCode(TEST_KEY, TEST_MESSAGE, code);
        assertFalse(result);
    }

    @Test
    void testValidateCode_returnsFalseForZeroCode() {
        var code = 0;

        var result = HealMAC.validateCode(TEST_KEY, TEST_MESSAGE, code);
        assertFalse(result);
    }

    @Test
    void testValidateCode_throwsForEmptyKey() {
        var exception = assertThrows(HealMACException.class, () -> {
                HealMAC.validateCode(new byte[]{}, TEST_MESSAGE, 0);
            });

        assertTrue(exception.getMessage().matches("empty key"));
    }

    @Test
    void testValidateCode_throwsForEmptyMessage() {
        var exception = assertThrows(HealMACException.class, () -> {
                HealMAC.validateCode(TEST_KEY, new byte[]{}, 0);
            });

        assertTrue(exception.getMessage().matches("empty message"));
    }

    private byte[] TEST_MESSAGE = "test message".getBytes();
    private byte[] TEST_KEY = {
        (byte)0xc4, (byte)0xdf, (byte)0x6c, (byte)0x42,
        (byte)0xd5, (byte)0xe5, (byte)0xe8, (byte)0xba,
        (byte)0x6e, (byte)0x37, (byte)0x32, (byte)0x4f,
        (byte)0x65, (byte)0x7e, (byte)0x8b, (byte)0x95,
        (byte)0x91, (byte)0x20, (byte)0x47, (byte)0x56,
        (byte)0x50, (byte)0x8b, (byte)0xbf, (byte)0xc9,
        (byte)0x30, (byte)0x33, (byte)0x4a, (byte)0xff,
        (byte)0xa0, (byte)0xb4, (byte)0x90, (byte)0xed
    };
}
