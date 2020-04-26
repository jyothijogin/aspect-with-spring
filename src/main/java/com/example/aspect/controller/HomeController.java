package com.example.aspect.controller;

import com.example.aspect.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SimpleService simpleService;

    public HomeController(SimpleService simpleService){
        this.simpleService = simpleService;
    }

    @GetMapping("home")
    public String example(){
        return "Welcome Home";
    }

    @GetMapping
    public String greet(){
        this.simpleService.doSomething();
        return "Hello";
    }

}
