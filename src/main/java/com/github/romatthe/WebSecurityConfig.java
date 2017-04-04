package com.github.romatthe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnauthenticatedHandler unauthenticatedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
        http
            // Disable CSRF tokens, as we don't have any cookie sessions
            .csrf().disable()
            // Handle any unauthenticated requests as a 401
            .exceptionHandling().authenticationEntryPoint(unauthenticatedHandler)
            .and()
            // No sessions are needed, we work with a stateless JWT authentication scheme
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // Permit unauthorized requests to the Login endpoint
            .antMatchers("/api/login").permitAll()
            // Do not permit unauthoried requests to any other API endpoints
            .antMatchers("api/**").permitAll().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

}
