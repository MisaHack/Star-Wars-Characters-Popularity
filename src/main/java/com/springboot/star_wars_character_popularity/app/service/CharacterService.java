package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.Character;

import java.util.List;

public interface CharacterService {
    Character saveCharacter(Character character);
    List<Character> getAllCharacters();
    Character getCharacterById(long id);
    Character updateCharacter(Character character, long id);
    void deleteCharacter(long id);
    void fetchCharactersOptimized();
}
