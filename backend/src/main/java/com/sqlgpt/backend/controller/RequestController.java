package com.sqlgpt.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @PostMapping("/getmockdata")
    public String getMockData() {
        return "Hello GPT!";
    }
}
