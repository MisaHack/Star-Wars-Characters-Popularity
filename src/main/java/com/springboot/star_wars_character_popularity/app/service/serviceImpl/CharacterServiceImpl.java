package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.help.CharacterPage;
import com.springboot.star_wars_character_popularity.app.help.PlanetPage;
import com.springboot.star_wars_character_popularity.app.model.Character;
import com.springboot.star_wars_character_popularity.app.model.Planet;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    public String planetsUri = "https://swapi.dev/api/planets/";

    private CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository){
        this.characterRepository=characterRepository;
    }

    @Override
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character getCharacterById(long id) {

        Optional<Character> character = characterRepository.findById(id);

        if(character.isPresent()){
            return character.get();
        }
        else{
            throw new ResourceNotFoundException("Character","id",id);
        }
    }

    @Override
    public Character updateCharacter(Character character, long id) {

        Character existingCharacter = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","id",id));

        existingCharacter.setName(character.getName());

        characterRepository.save(existingCharacter);

        return existingCharacter;
    }

    @Override
    public void deleteCharacter(long id) {

        characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","id",id));

        characterRepository.deleteById(id);
    }


}
