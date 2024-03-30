package com.ayd2.intelafbackend.services.jwt;

import com.ayd2.intelafbackend.enums.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    public static final String SECRET_PHASE = "bmpJGkpYz0Af4ub65tzlnPRX2De1o02uuStUt2y1nhgAXzhngZJtWOgVAlOWYD41";

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .claims(Collections.singletonMap("role", Role.ADMIN))
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 900000))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    @Override
    public boolean isValid(String token) {
        Claims claims = extractClaims(token);
        Date expirationDate = claims.getExpiration();

        return new Date().before(expirationDate);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_PHASE);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
