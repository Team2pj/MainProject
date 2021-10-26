package com.project.website.controller;

import com.project.website.dto.BoardDto;
import com.project.website.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;





    @GetMapping("/communicate")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Principal principal) {
        List<BoardDto> boardDtoList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        String username = principal.getName();
        model.addAttribute("postList", boardDtoList);
        model.addAttribute("pageList",pageList);
        model.addAttribute("username",username);

        return "board/list";
    }

    @GetMapping("/post")
    public String write() {
        return "board/post";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/communicate";
    }


    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        boardService.updateCount(id);
        model.addAttribute("post",boardDto);
        return "board/detailBoard";
    }

    @GetMapping("/post/update/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto",boardDto);
        return "board/updateBoard";
    }

    @PutMapping("/post/update/{id}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }
    


    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList",boardDtoList);

        return "board/list";
    }



}
