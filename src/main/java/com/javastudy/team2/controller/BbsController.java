package com.javastudy.team2.controller;


import com.javastudy.team2.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class BbsController {

    @GetMapping("/hello")
    public  String hellowView(Model model){
        model.addAttribute("msg", "ok");

        List<String> list  = Arrays.asList( "a", "b", "c");

        model.addAttribute("list", list);

        model.addAttribute("url", 33);

        model.addAttribute("sampleDTO", new SampleDTO());

        return  "hello";
    }


    @GetMapping("/testview")
    public  String testView(Model model){
        model.addAttribute("attributeName", "attributeValue");

        List<String> list  = Arrays.asList( "List 1", "List 2", "List 3", "List 4", "List 5", "List 6", "List 7");

        model.addAttribute("list", list);

        model.addAttribute("url", 33);



        return  "folder1/testview";
    }


    @GetMapping("/link")
    public  String linkTest(){



        return  "link";
    }

    @GetMapping("/link/{id}")
    public  String linkView(@PathVariable("id") int id, Model model){
        model.addAttribute("id", id);
        return  "linkd";
    }



    @PostMapping("/testDTO")
    //public void dtotest(@RequestBody HashMap<String, String> param){
    public String formTest(@ModelAttribute("sampleDTO") SampleDTO sampleDTO) {
        return "redirect:/";
    }
}
