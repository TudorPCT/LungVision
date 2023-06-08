package com.lungvision.lungdiseasexrayclassificationbe.security.authentification;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private UserDetails userDetails;

    public AuthenticationToken(UserDetails userDetails, String token) {
        super(userDetails.getAuthorities());
        this.token = token;
        this.userDetails = userDetails;
        setAuthenticated(true);
    }

    public AuthenticationToken(String token) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUsername();
    }
}
