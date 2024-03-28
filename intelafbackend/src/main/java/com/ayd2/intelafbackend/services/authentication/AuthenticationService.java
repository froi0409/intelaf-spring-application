package com.ayd2.intelafbackend.services.authentication;

import com.ayd2.intelafbackend.dto.authentication.AuthenticationRequestDTO;
import com.ayd2.intelafbackend.dto.authentication.JwtResponseDTO;

public interface AuthenticationService {

    JwtResponseDTO authenticateAndGetToken(AuthenticationRequestDTO authDTO);

}
