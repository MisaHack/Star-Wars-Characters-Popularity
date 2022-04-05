package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/characterlistdb")
public class CharacterListDBController {

    private CharacterService characterService;

    public CharacterListDBController(CharacterService characterService){
        this.characterService = characterService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public ModelAndView listCharacters(Model theModel){

        //load characters data from db
        List<CharacterModel> characters = characterService.getAllCharacters();

        // add to the spring model
        theModel.addAttribute("characters", characters);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/list-characters");

        return modelAndView;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(Model theModel){

        // create model attribute to bind form data
        CharacterModel character = new CharacterModel();

        theModel.addAttribute("character", character);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/character-form");

        return modelAndView;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("characterId") long id, Model theModel){

        // get the character from the service
        CharacterModel character = characterService.getCharacterById(id);

        // set character as a model attribute to pre-populate the form
        theModel.addAttribute("character", character);

        // send over to our form
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/character-form");

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("characterId") long id){

        // delete the character
        characterService.deleteCharacter(id);

        // redirect to /api/character/list
        return new ModelAndView("redirect:/api/characterlistdb/list");
    }
}
