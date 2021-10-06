package com.project.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about_page(){
        return "about";
    }
/*
    @GetMapping("/contact")
    public String contact_page(){
        return "contact";
    }

 */

    @GetMapping("/gallery")
    public String gallery_page(){
        return "gallery";
    }

    @GetMapping("/packages")
    public String packages_page(){
        return "packages";
    }

    @GetMapping("/pricing")
    public String pricing_page(){
        return "pricing";
    }
}
