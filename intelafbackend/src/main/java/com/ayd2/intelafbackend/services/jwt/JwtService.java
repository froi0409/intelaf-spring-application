package com.ayd2.intelafbackend.services.jwt;

public interface JwtService {

    String generateToken(String username);

    String getUsername(String token);

    boolean isValid(String token);

}