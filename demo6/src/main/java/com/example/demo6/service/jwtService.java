package com.example.demo6.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Service
public class jwtService {
    String secret="Hello world";
    long expirtytime=1000*60*60*24*1; //1day
    String issuer="vishalgandla.com";
    public String createToken(String userId) {
        // Define the signing algorithm with a secret key
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // Set the expiration date (e.g., 10 minutes from now)
        Date expirationDate = new Date(System.currentTimeMillis() + expirtytime);

        // Create the token with claims
        String token = JWT.create()
                .withIssuer(issuer)                // Set the issuer
                .withSubject(userId)                // Set a subject claim (e.g., user ID)
                .withIssuedAt(new Date())           // Set issued at date
                .withExpiresAt(expirationDate)      // Set expiration date
                .sign(algorithm);                   // Sign the token with the algorithm
        return token;
    }


    public boolean validateToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            Instant expirationTime = decodedJWT.getExpiresAt().toInstant();
            if (expirationTime.isBefore(Instant.now())) {

                return false;
            }
            return true;

        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getSubject(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secret);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            return decodedJWT.getSubject();
        }
        catch (Exception e){
            return null;
        }
    }



}
