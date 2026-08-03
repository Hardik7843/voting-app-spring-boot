package com.voting.app;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretKey = "";

    public JwtService() {
        try {

            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();

            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60*60*60*60*60))
                .and()
                .signWith(getKey()).compact();

    }

    public String isTokenValid(String token) {
        try {
            Key key = getKey();
            System.out.println("key: "+ key);

            String email = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token).getPayload().getSubject();
            return email;
        } catch (JwtException | IllegalArgumentException ex) {
            System.out.println(ex);
            return "Token Not Valid";
        }
    }

    private Key getKey() {
        String secret = "Ui6u+OZceIxn+xQTt+W+ycBaAXx5z3msufNSrb2nw+M=";
//        System.out.println("secretKey: " + secretKey);
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
