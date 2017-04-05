package com.github.romatthe.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtAuthenticationFailureHandler failureHandler;

    // TODO Not sure if below means the filter will be applied to ALL routers or only the ones wired up in WebSecurityConfig
    protected JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // Apply JWT checks

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")) {
            String token = header.substring(7, header.length());

            // TODO Validation should go here
            // 1. Validate the token
            // 2. Get the UserName from the Token
            // 3. Get the authorized roles from the token

            // TODO Turn token String into JwtToken
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(token);

            return getAuthenticationManager().authenticate(authToken);
        }
        else {
            // IMPOSTER ALERT
            // TODO Is return null correct?
            throw new TokenNotProvidedException("Not Jwt token was provided");
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
