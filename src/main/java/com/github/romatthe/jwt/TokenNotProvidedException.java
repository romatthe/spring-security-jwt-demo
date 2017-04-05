package com.github.romatthe.jwt;

import org.springframework.security.core.AuthenticationException;

public class TokenNotProvidedException extends AuthenticationException {

    public TokenNotProvidedException(String msg) {
        super(msg);
    }

}
