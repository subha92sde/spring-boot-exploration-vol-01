package com.spring.boot.exploration.general;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class SecretKeyGenerator {
    private final String secretKey;

    public SecretKeyGenerator() {
        this.secretKey = generateSecretKey();
        System.out.println("Generated secretKey: " + this.secretKey); // Debugging purpose; remove in production
    }

    private String generateSecretKey() {
        try {
            // Use KeyGenerator to create a secure 256-bit key
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256); // Set the key size to 256 bits
            SecretKey key = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded()); // Encode to Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    public String getSecretKey() {
        return secretKey;
    }
}
