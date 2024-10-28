package com.example.demo4.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class jwtService {
    private static final String SECRET_KEY = "your-secret-key";  //secret signing key
    long expirationTimeInMs = 3600000*24*3;//expiration time of 3 days

    public String generateToken(String username){
        return JWT.create()
                .withSubject(username)                   // Set the subject (e.g., username)
                .withIssuedAt(new Date())               // Set issued at time
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInMs)) // Expiration time
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public  String getSubject(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public boolean verifyToken(String token) {//check with the key and for expiry
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT decodedJWT =verifier.verify(token);
            Date expiration = decodedJWT.getExpiresAt();
            if (expiration != null && expiration.before(new Date())) {
                return false;
            }
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }


}
