package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.CharacterModel;

import java.util.List;

public interface CharacterService {
    CharacterModel saveCharacter(CharacterModel character);
    List<CharacterModel> getAllCharacters();
    CharacterModel getCharacterById(long id);
    CharacterModel updateCharacter(CharacterModel character, long id);
    void deleteCharacter(long id);
}
