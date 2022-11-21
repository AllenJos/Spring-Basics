package com.example.demosecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String greet(){
        return "Hello World!";
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "Testing the code!";
    }

    @GetMapping("/developcode")
    public String developCode(){
    return "Developing the code!";
    }

    @GetMapping("/accessserver")
    public String accessServer(){
        return "Accessing the server!";
    }

    @GetMapping("/home")
    public String home(){
        return "Home Page!";
    }
}
