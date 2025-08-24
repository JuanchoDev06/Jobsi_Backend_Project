package com.escaes.jobsy.infraestructure.service;

import com.escaes.jobsy.config.jwt.JwtProvider;
import com.escaes.jobsy.domain.repository.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return jwtProvider.generateToken(authentication);
    }
}
