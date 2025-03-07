package me.ilizin.spring_demo.springboot_demo.api_rest_with_security_demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloWorldRestController {

    @GetMapping("greetings")
    public String greetingsWithGet() {
        return "Hello World!";
    }

    @PutMapping("greetings")
    public String greetingsWithPut() {
        return "Hello World!";
    }

    @PostMapping("greetings")
    public String greetingsWithPost() {
        return "Hello World!";
    }

    @DeleteMapping("greetings")
    public String greetingsWithDelete() {
        return "Hello World!";
    }
}
