package com.example.authapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtTokenProvider {
    private static final String SECRET_KEY = "x2%u5G8e!)jL-`_K^UMYp}";
    private static final long EXPIRATION_TIME = 864_000_00; //24 hours in milliseconds

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));
    }
}
