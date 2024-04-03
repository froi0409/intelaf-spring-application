package com.ayd2.intelafbackend.dto.authentication;

import com.ayd2.intelafbackend.enums.user.Role;
import lombok.Value;

@Value
public class JwtResponseDTO {
    private final String token;

    private final Role role;
}
