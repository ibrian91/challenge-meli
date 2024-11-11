package com.mercadolibre.mutant_detector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hola Mundo desde Spring Boot!";
    }
}
