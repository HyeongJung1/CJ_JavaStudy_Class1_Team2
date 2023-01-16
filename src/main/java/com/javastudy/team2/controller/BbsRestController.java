package com.javastudy.team2.controller;


import com.javastudy.team2.dto.SampleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api2")
public class BbsRestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/axiostest")
    public List<Integer> axiosTest(@RequestBody HashMap<String, String> param){
        List<Integer>  intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return  intList;
    }


    @PostMapping("/axiostest2")
    public List<String> axiosTest2(@RequestBody HashMap<String, String> param){
        List<String>  intList = Arrays.asList("AAA","BBB","CCC","DDD","EEE","FFF","GGG","HHH","III","JJJ","KKK");
        return  intList;
    }

    @PostMapping("/dtotest")
    //public void dtotest(@RequestBody HashMap<String, String> param){
    public void dtotest(@RequestBody SampleDTO sampleDTO) {
        String first = sampleDTO.getFirst();
        String second = sampleDTO.getSecond();
        String last = sampleDTO.getLast();
        System.out.println(sampleDTO.toString());
    }


    @GetMapping("/maptest")
    public ResponseEntity<Map<String, String>> mapTest() {

        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("param1", "val1");
        resultMap.put("param2", "val2");
        resultMap.put("param3", "val3");
        resultMap.put("param4", "val4");
        resultMap.put("param5", "val5");

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


}
