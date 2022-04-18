package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.Character;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @PostMapping("/save")
    public ModelAndView saveCharacter(@ModelAttribute("character") Character character){

       characterService.saveCharacter(character);

       return new ModelAndView("redirect:/api/characterlistdb/list");
    }

    @GetMapping
    public List<Character> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable("id") long character_id){
        return new ResponseEntity<Character>(characterService.getCharacterById(character_id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable("id") long id, @RequestBody Character character){
       return new ResponseEntity<Character>(characterService.updateCharacter(character, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable("id") long id){

        characterService.deleteCharacter(id);

        return new ResponseEntity<String>("Character deleted successfully !", HttpStatus.OK);
    }
}
