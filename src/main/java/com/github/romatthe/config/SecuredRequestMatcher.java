package com.github.romatthe.config;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class SecuredRequestMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        return
            Arrays.asList("/api/user", "/api/cat")
                .contains(request.getRequestURI());
    }

}
