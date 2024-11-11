package com.mercadolibre.mutant_detector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hola Mundo desde Spring Boot!";
    }
}
