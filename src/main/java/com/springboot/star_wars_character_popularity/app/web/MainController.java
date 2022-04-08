package com.springboot.star_wars_character_popularity.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/login")
    public ModelAndView login(){

       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("users/login");

       return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        return modelAndView;
    }
}
