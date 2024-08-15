package com.example.authapi.controller;

import com.example.authapi.util.JwtTokenProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @GetMapping("/token")
    public String getToken(@RequestParam String username) {
        return JwtTokenProvider.generateToken(username);
    }
}
