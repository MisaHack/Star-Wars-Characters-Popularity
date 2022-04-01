package com.springboot.star_wars_character_popularity.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {

    //------------ Thymeleaf controllers ----------------

    /*
    @GetMapping("/hello/hello")
    public String sayHello(Model theModel){
        theModel.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }
     */

    @GetMapping
    public ModelAndView sayHello(Model theModel){

        theModel.addAttribute("theDate", new java.util.Date());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("helloworld");

        return modelAndView;
    }
}
