package com.ayd2.intelafbackend.services.jwt;

import com.ayd2.intelafbackend.enums.user.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

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
                .claims(Collections.singletonMap("1", Role.ADMIN))
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 900000))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_PHASE);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
