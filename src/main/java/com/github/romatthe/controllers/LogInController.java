package com.github.romatthe.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class LogInController {

    @PostMapping(path = "/login")
    public void logIn() {
        
    }

}
