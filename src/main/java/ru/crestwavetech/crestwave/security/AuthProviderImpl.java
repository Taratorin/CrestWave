package ru.crestwavetech.crestwave.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class Provider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
