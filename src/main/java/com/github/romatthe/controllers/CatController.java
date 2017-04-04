package com.github.romatthe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatController {

    @GetMapping("/cat")
    public String getCats() {
        return "Muesli, Luna, Aiki";
    }

}
