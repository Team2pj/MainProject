package com.project.website.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class RecordingPageController {

    @GetMapping("/recording")
    public String reco() {
        return "video/recording";
    }
}
