package com.challenge.costumer.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Set;

@Service
public class TokenServiceImpl implements TokenService {

    private static final long EXPIRATION_SECONDS = 3600;
    private static final Set<String> VALID_USERS = Set.of("admin");

    @Override
    public String generateToken(String subject) {
        long expiryEpochSeconds = Instant.now().getEpochSecond() + EXPIRATION_SECONDS;
        String tokenData = subject + ":" + expiryEpochSeconds;
        return Base64.getEncoder().encodeToString(tokenData.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decoded.split(":");

            if (parts.length != 2) {
                return false;
            }

            String subject = parts[0];
            long expiry = Long.parseLong(parts[1]);

            return VALID_USERS.contains(subject) && Instant.now().getEpochSecond() < expiry;
        } catch (Exception e) {
            return false;
        }
    }

}
