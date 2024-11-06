package com.clearout.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open")
public class OpenController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}