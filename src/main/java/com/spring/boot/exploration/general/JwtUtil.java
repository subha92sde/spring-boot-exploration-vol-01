package com.spring.boot.exploration.general;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 2; // 2 minutes

    public JwtUtil(@Qualifier("jwtSecretKey") String jwtSecretKey) {
        this.secretKey = jwtSecretKey;
        System.out.println("Injected secretKey: " + this.secretKey); // Debug
    }

    public String generateToken(String ssn) {
        return Jwts.builder()
                .setSubject(ssn) // Store the SSN as the subject
                .setIssuedAt(new Date()) // Current timestamp
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry timestamp
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign with HS256 and the secret key
                .compact();
    }

    public String extractSsn(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public boolean validateToken(String token, String ssn) {
        final String extractedSsn = extractSsn(token);
        return (extractedSsn.equals(ssn) && !isTokenExpired(token));
    }
}
