package com.ayd2.intelafbackend.controllers.authentication;

import com.ayd2.intelafbackend.dto.authentication.AuthenticationRequestDTO;
import com.ayd2.intelafbackend.dto.authentication.JwtResponseDTO;
import com.ayd2.intelafbackend.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@RequestBody AuthenticationRequestDTO authDTO) {
        return ResponseEntity.ok(authenticationService.authenticateAndGetToken(authDTO));
    }
}
