package com.aplazo.challenge.config.interceptor;

import com.aplazo.challenge.exception.UnauthorizedRequest;
import com.aplazo.challenge.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.aplazo.challenge.exception.ErrorCode.UNAUTHORIZED_MESSAGE;

@Component
@RequiredArgsConstructor
public class AuthTokenInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String uri = request.getRequestURI();
        String method = request.getMethod();

        if ("/v1/customers".equals(uri) && "POST".equalsIgnoreCase(method)) {
            return true;
        }

        String token = request.getHeader("X-Auth-Token");

        if (!tokenService.isTokenValid(token)) {
            throw new UnauthorizedRequest(UNAUTHORIZED_MESSAGE);
        }

        return true;
    }
}
