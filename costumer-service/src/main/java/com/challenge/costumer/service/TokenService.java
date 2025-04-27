package com.challenge.costumer.service;

public interface TokenService {

    String generateToken(String subject);

    boolean isTokenValid(String token);

}
