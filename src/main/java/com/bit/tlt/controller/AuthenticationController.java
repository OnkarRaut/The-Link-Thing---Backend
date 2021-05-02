package com.bit.tlt.controller;

import com.bit.tlt.model.AuthenticationRequest;
import com.bit.tlt.model.AuthenticationResponse;
import com.bit.tlt.service.AuthenticationService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(
            AuthenticationResponse.builder()
                .token(this.authenticationService.authenticate(authenticationRequest))
                .build()
        );
    }

}
