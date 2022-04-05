package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/characterlist")
public class CharacterListController {

    //load character data

    private List<CharacterModel> characters;

    @PostConstruct
    private void loadData(){

        // create characters
        CharacterModel character1 = new CharacterModel("Luke Skywalker", "Tatooine");
        CharacterModel character2 = new CharacterModel("C-3PO", "Tatooine");
        CharacterModel character3 = new CharacterModel("R2-D2", "Naboo");
        CharacterModel character4 = new CharacterModel("Darth Vader", "Tatooine");

        // create the list
        characters = new ArrayList<>();

        // add to the list
        characters.add(character1);
        characters.add(character2);
        characters.add(character3);
        characters.add(character4);

    }

    // add mapping for "/list"
    @GetMapping("/list")
    public ModelAndView listCharacters(Model theModel){

        // add to the spring model
        theModel.addAttribute("characters", characters);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/list-characters");

        return modelAndView;
    }
}
