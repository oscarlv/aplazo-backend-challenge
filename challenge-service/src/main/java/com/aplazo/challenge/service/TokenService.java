package com.aplazo.challenge.service;

public interface TokenService {

    String generateToken(String subject);

    boolean isTokenValid(String token);

}
