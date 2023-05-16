package com.lungvision.lungdiseasexrayclassificationbe.security.filter;

import com.lungvision.lungdiseasexrayclassificationbe.security.service.JwtUtil;
import com.lungvision.lungdiseasexrayclassificationbe.security.service.UserSecurityDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/api/login");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException {

        String email = request.getHeader("email");
        String password = request.getHeader("password");
        UserDetails userDetails = userSecurityDetailsService.loadUserByUsername(email);
        final String token = jwtUtil.generateToken(userDetails);

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();
        auth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
