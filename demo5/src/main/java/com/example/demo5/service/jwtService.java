package com.example.demo5.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class jwtService {

    private long expiration_time=1000 * 60 * 60*24;//1day
    private String secretKey="Hello world";
    public String generateToken(String id) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey); // Use HMAC256 algorithm with the secret key

        // Build the JWT token
        return JWT.create()
                .withSubject(id) // Set the subject (usually the username or user ID)
                .withIssuedAt(new Date()) // Set the issued time
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration_time)) // Set the expiration time (1 hour)
                .sign(algorithm); // Sign the JWT with the algorithm and the secret key
    }

    public String extractSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);

            // Return the subject from the token
            return decodedJWT.getSubject();
        } catch (Exception e) {
            // Handle exceptions (e.g., token is invalid, expired, etc.)
            System.out.println("Invalid or expired token: " + e.getMessage());
            return null;
        }
    }
    public boolean isTokenExpired(String token) {
        try {

            DecodedJWT decodedJWT = JWT.decode(token); // Decodes the token without validating it

            Date expirationDate = decodedJWT.getExpiresAt();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (Exception e) {
            // Handle decoding errors (e.g., invalid token format)
            return true; // Assume the token is expired if an error occurs
        }
    }
}
