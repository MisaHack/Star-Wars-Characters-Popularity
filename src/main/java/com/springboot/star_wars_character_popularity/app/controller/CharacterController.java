package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.Character;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @PostMapping
    public ModelAndView saveCharacter(@ModelAttribute("character") Character character){

        characterService.saveCharacter(character);

        return new ModelAndView("redirect:/api/character");
    }

    @GetMapping
    public ModelAndView listCharacters(Model theModel){

        List<Character> characters = characterService.getAllCharacters();

        theModel.addAttribute("characters", characters);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/list-characters");

        return modelAndView;
    }



    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(Model theModel){

        Character character = new Character();

        theModel.addAttribute("character", character);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/character-form");

        return modelAndView;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("characterId") long id, Model theModel){

        Character character = characterService.getCharacterById(id);

        theModel.addAttribute("character", character);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("characters/character-form");

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("characterId") long id){

        characterService.deleteCharacter(id);

        return new ModelAndView("redirect:/api/character/");
    }

    @GetMapping("/fetch-characters")
    public void getCharacterData(){
        characterService.fetchCharactersOptimized();
    }

}