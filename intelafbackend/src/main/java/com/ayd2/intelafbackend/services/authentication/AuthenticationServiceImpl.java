package com.ayd2.intelafbackend.services.authentication;

import com.ayd2.intelafbackend.dto.authentication.AuthenticationRequestDTO;
import com.ayd2.intelafbackend.dto.authentication.JwtResponseDTO;
import com.ayd2.intelafbackend.services.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public JwtResponseDTO authenticateAndGetToken(AuthenticationRequestDTO authDTO) {
        UsernamePasswordAuthenticationToken authData
                = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            if (authentication.isAuthenticated()) {
                jwtService.updateTokenExpiration(authDTO.getUsername());
                return new JwtResponseDTO(jwtService.generateToken(authDTO.getUsername()));
            }
        } catch (AuthenticationException ex) {
            log.error("Error authenticating", ex);
        }
        throw new UsernameNotFoundException("Invalid user request");
    }

}
