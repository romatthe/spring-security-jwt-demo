package com.github.romatthe.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;

        // TODO Retrieve the correct claims from the token
        Jws<Claims> jwsClaims = null;

        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);

        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // TODO Create the new User Context with the correct subjects and authorities, and put these in the new Authentication object
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // This AuthenticationProvider only functions for JwtAuthenticationTokens
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
