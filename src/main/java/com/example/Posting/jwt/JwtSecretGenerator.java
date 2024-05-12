package com.example.Posting.jwt;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] secretBytes = new byte[64]; // 64 bytes = 512 bits
        random.nextBytes(secretBytes);
        
        String secret = Base64.getEncoder().encodeToString(secretBytes);
        System.out.println("Generated Secret: " + secret);
    }
}
