package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository){
        this.characterRepository=characterRepository;
    }

    @Override
    public CharacterModel saveCharacter(CharacterModel character) {
        return characterRepository.save(character);
    }

    @Override
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public CharacterModel getCharacterById(long id) {

        Optional<CharacterModel> character = characterRepository.findById(id);

        if(character.isPresent()){
            return character.get();
        }
        else{
            throw new ResourceNotFoundException("Character","id",id);
        }
    }

    @Override
    public CharacterModel updateCharacter(CharacterModel character, long id) {

        CharacterModel existingCharacter = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","id",id));

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
