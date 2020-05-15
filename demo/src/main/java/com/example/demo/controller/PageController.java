package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/summarization")
public class PageController {
    @RequestMapping("/demo")
    public String hello(){
        return "/html/mainPage.html";
    }
}
