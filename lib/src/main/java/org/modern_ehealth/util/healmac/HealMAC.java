package org.modern_ehealth.util.healmac;

import com.google.common.hash.Hashing;

public class HealMAC {

    private static final int MURMUR3_SEED = 2064860571; // Randomly chosen

    public static byte[] generateCode(byte[] key, byte[] message) {
        if(key.length == 0) {
            throw new HealMACException("empty key");
        }

        if(message.length == 0) {
            throw new HealMACException("empty message");
        }

        var hf = Hashing.murmur3_128(MURMUR3_SEED);
        var hasher = hf.newHasher();

        // TODO: Check if this matches the HMAC RFC???
        hasher.putBytes(key);
        hasher.putBytes(message);

        return hasher.hash().asBytes();
    }

    public static boolean validateCode(byte[] key, byte[] message, byte[] code) {
        // Calculate the correct code
        var actualCode = generateCode(key, message);

        // If the lengths are not equal, code is not correct
        if(code.length != actualCode.length) {
            return false;
        }

        // If any byte doesn't match, code is not correct
        for(var i = 0; i < code.length; i++) {
            if(code[i] != actualCode[i]) {
                return false;
            }
        }

        return true;
    }
}
