package com.ayd2.intelafbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/client")
public class ClientController {

    @GetMapping
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
