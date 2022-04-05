package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<CharacterModel> saveCharacter(@RequestBody CharacterModel character){
       return new ResponseEntity<CharacterModel>(characterService.saveCharacter(character), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CharacterModel> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterModel> getCharacterById(@PathVariable("id") long character_id){
        return new ResponseEntity<CharacterModel>(characterService.getCharacterById(character_id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterModel> updateCharacter(@PathVariable("id") long id, @RequestBody CharacterModel character){
       return new ResponseEntity<CharacterModel>(characterService.updateCharacter(character, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable("id") long id){

        characterService.deleteCharacter(id);

        return new ResponseEntity<String>("Character deleted successfully !", HttpStatus.OK);
    }
}
