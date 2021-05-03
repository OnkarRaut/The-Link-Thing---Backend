package com.bit.tlt.service;

import com.bit.tlt.model.AuthenticationRequest;
import com.bit.tlt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@ConditionalOnProperty(name = "security.auth.enabled", havingValue = "true")
@Component
public class AuthenticationService {

    @Value("${security.auth.key}")
    private String key;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(AuthenticationRequest authenticationRequest) {
        Authentication auth = this.authenticationManager
            .authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword(),
                        Collections.emptyList()
                )
            );

        User user = (User) auth.getPrincipal();
        return JWTUtil.generateToken(user.getUsername(), key, null);
    }

}
