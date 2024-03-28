package com.ayd2.intelafbackend.dto.authentication;

import lombok.Value;

@Value
public class AuthenticationRequestDTO {
    private final String username;
    private final String password;
}
