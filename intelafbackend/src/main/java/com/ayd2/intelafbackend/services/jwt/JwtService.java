package com.ayd2.intelafbackend.services.jwt;

import com.ayd2.intelafbackend.enums.user.Role;

public interface JwtService {

    String generateToken(String username, Role role);

    String getUsername(String token);

    boolean isValid(String token);

    void updateTokenExpiration(String username);

    public boolean isTokenExpired(String username);

}
