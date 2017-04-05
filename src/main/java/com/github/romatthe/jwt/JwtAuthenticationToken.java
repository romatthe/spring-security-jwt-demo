package com.github.romatthe.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;

// TODO Not sure if this imolementation makes any sense?
// TODO Probably needs list of roles/authorities?
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private final Object principal = "TEMP";
    private final Object details = "TEMP";

    public JwtAuthenticationToken(String jwtToken) {
        super(null);
        super.setAuthenticated(true);

        // TODO Parse the JWT token String
        // this.principal = token.getSub();
        // this.setDetailsAuthorities
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
