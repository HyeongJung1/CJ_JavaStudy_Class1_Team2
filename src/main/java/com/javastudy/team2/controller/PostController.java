package com.javastudy.team2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {


    @GetMapping("/")
    private String indexView()
    {

        return "redirect:/postlist";
    }

    @GetMapping("/postlist")
    //private String index(Model model, @PageableDefault(sort ="id", direction = Sort.Direction.DESC))
    private String index()
    {

        return "postlist";
    }

}
