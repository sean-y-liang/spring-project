package com.example.customermanagement.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {

    private static final String SECRET_KEY = "x2%u5G8e!)jL-`_K^UMYp}";

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint accessible";
    }

    @GetMapping("/secure")
    public String secureEndpoint(@RequestHeader("Authorization") String token, HttpServletResponse response) throws IOException {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
                return null;
            }

            String jwtToken = token.replace("Bearer ", "");
            JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build()
                    .verify(jwtToken);

            return "Secure endpoint accessible";
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return null;
        }
    }
}
