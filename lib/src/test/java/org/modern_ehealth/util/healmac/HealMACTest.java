package org.modern_ehealth.util.healmac;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HealMACTest {

    // generateCode

    @Test
    void testGenerateCode_calculatesMurmur3HMAC() {
        var hmac = HealMAC.generateCode(TEST_KEY, TEST_MESSAGE);

        // Calculated from python mmh3
        var expected = new byte[]{
            (byte)0x10, (byte)0x01, (byte)0x39, (byte)0xdc,
            (byte)0xf9, (byte)0x29, (byte)0x0b, (byte)0x23,
            (byte)0xeb, (byte)0xe2, (byte)0x76, (byte)0xed,
            (byte)0x38, (byte)0x31, (byte)0x2f, (byte)0xe6
        };

        assertArrayEquals(expected, hmac);
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
