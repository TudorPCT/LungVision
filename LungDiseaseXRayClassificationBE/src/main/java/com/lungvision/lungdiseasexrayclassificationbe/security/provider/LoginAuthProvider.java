package com.lungvision.lungdiseasexrayclassificationbe.security.provider;

import com.lungvision.lungdiseasexrayclassificationbe.security.service.UserSecurityDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAuthProvider implements AuthenticationProvider {
    private final UserSecurityDetailsService userSecurityDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userSecurityDetails = userSecurityDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(password, userSecurityDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, userSecurityDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
