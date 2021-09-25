package com.project.website.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class VideoStreamController {


    @GetMapping("/cctvstream")
    public String CCTVS() {
        return "video/cctvstream";
    }




}
