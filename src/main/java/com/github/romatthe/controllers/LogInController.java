package com.github.romatthe.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class LogInController {

    @PostMapping(path = "/login")
    public String logIn() throws IOException, URISyntaxException {
        URI uri = getClass().getClassLoader().getResource("response.txt").toURI();
        List<String> lines = Files.readAllLines(Paths.get(uri), Charset.defaultCharset());

        return String.join("\n", lines);
    }

}
