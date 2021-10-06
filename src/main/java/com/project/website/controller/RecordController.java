package com.project.website.controller;

import com.project.website.dto.RecordDto;
import com.project.website.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class RecordController {

    private RecordService recordService;

    public RecordController(RecordService recordService){
        this.recordService = recordService;
    }

    @GetMapping("/record")
    public String reco() {
        return "video/recording";
    }

    @GetMapping("test")
    public String list(Model model){
        List<RecordDto> recordDtoList = recordService.getRecordList();
        model.addAttribute("recordList", recordDtoList);
        return "/test";
    }


}


