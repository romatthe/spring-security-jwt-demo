package com.github.romatthe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @GetMapping(path = "/user")
    public String getUsers() {
        return "Jefke";
    }

}
