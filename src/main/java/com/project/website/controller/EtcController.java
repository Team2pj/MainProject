package com.project.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/About")
    public String about_page() {
        return "about";
    }

    @GetMapping("/Service")
    public String packages_page() {
        return "service";
    }

    @GetMapping("/CCTV")
    public String gallery_page() {
        return "cctv";
    }
}

