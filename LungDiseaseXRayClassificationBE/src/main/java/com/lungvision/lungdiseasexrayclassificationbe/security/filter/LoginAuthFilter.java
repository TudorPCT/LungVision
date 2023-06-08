package com.lungvision.lungdiseasexrayclassificationbe.security.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lungvision.lungdiseasexrayclassificationbe.security.service.JwtUtil;
import com.lungvision.lungdiseasexrayclassificationbe.security.service.UserSecurityDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class LoginAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserSecurityDetailsService userSecurityDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/api/auth/login");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException {


        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());

        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();

        UserDetails userDetails;
        final String token;
        Authentication auth;

        try {
            userDetails = userSecurityDetailsService.loadUserByUsername(email);
            token = jwtUtil.generateToken(userDetails);

            auth = new UsernamePasswordAuthenticationToken(email, password);

            auth = authenticationManager.authenticate(auth);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"" + e.getMessage() + "\"}");
            response.getWriter().flush();
            return;
        }

        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
