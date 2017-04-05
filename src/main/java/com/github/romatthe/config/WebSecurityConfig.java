package com.github.romatthe.config;

import com.github.romatthe.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnauthenticatedHandler unauthenticatedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()                                                               // Disable CSRF tokens, as we don't have any cookie sessions
            .exceptionHandling().authenticationEntryPoint(unauthenticatedHandler)           // Handle any unauthenticated requests as a 401
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)                 // No sessions are needed, we work with a stateless JWT authentication scheme
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/login").permitAll()      // Permit unauthorized requests to the Login endpoint
            .and()
                .authorizeRequests()
                    .antMatchers("/api/**").authenticated()                     // Do not permit unauthoried requests to any other API endpoints
            .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

}
