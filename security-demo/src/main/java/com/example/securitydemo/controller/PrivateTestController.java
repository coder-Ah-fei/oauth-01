package com.example.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/private")
public class PrivateTestController {

    @GetMapping(value = "/test1")
    public String test1() {
        return "private/test1";
    }

    @GetMapping(value = "/test2")
    public String test2() {
        return "private/test2";
    }
}
