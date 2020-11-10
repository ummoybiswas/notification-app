package com.sqh.notif.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/generate-firebase-token")
    public String generateFirebaseToken() {
        return "firebase";
    }
}
