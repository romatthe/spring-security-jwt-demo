package com.github.romatthe;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Apply JWT checks

        String header = request.getHeader("Authorization");
        //header.startsWith("Bearer "); // Blah blah

        // 1. Validate the token
        // 2. Get the UserName from the Token
        // 3. Get the authorized roles from the token

        filterChain.doFilter(request, response);
    }

}
