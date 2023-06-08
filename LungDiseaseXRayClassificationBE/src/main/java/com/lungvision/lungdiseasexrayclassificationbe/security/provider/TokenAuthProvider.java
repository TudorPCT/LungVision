package com.lungvision.lungdiseasexrayclassificationbe.security.provider;

import com.lungvision.lungdiseasexrayclassificationbe.security.authentification.AuthenticationToken;
import com.lungvision.lungdiseasexrayclassificationbe.security.service.JwtUtil;
import com.lungvision.lungdiseasexrayclassificationbe.service.user.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthProvider implements AuthenticationProvider {
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) (authentication.getCredentials());
        String id;
        try{
            id = jwtUtil.getUsernameFromToken(token);
        } catch (Exception e){
            throw new JwtException("Authentication error");
        }

        if (id == null || !jwtUtil.validateToken(token)) {
            throw new JwtException("Authentication error");
        }

        return new AuthenticationToken(userService.loadUserById(id), token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.equals(authentication);
    }
}
