package com.example.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
public class PublicTestController {


    @GetMapping(value = "/test1")
    public String test1() {
        return "public/test1";
    }

    @GetMapping(value = "/test2")
    public String test2() {
        return "public/test2";
    }
}
