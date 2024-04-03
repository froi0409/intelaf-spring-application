package com.ayd2.intelafbackend.services.authentication;

import com.ayd2.intelafbackend.dto.authentication.AuthenticationRequestDTO;
import com.ayd2.intelafbackend.dto.authentication.JwtResponseDTO;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public JwtResponseDTO authenticateAndGetToken(AuthenticationRequestDTO authDTO) throws EntityNotFoundException {
        User userToAuth = userRepository.findByUsername(authDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", authDTO.getUsername())));

        UsernamePasswordAuthenticationToken authData
                = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            if (authentication.isAuthenticated()) {
                jwtService.updateTokenExpiration(authDTO.getUsername());
                return new JwtResponseDTO(jwtService.generateToken(authDTO.getUsername(), userToAuth.getRole()), userToAuth.getRole());
            }
        } catch (AuthenticationException ex) {
            log.error("Error authenticating", ex);
        }
        throw new UsernameNotFoundException("Invalid user request");
    }

}
