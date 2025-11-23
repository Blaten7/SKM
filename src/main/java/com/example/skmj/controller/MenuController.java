package com.example.skmj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping("/system")
    public String system() {
        return "system/systemMain";
    }
}
