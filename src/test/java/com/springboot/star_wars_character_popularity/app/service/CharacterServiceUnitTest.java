package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class CharacterServiceUnitTest {

    private CharacterServiceImpl characterService;

    private CharacterRepository characterRepositoryMock;

    @BeforeEach
    void setup(){
        this.characterRepositoryMock = mock(CharacterRepository.class);

        this.characterService = new CharacterServiceImpl(characterRepositoryMock);
    }

    @Test
    void shouldReturnSavedCharacter(){
        //given
        CharacterModel characterModel = new CharacterModel("Jedi", "Naboo");

        when(this.characterRepositoryMock.save(characterModel)).thenReturn(new CharacterModel("Jedi", "Tatooine"));

        CharacterModel expectedCharacter = new CharacterModel("Jedi", "Naboo");

        //when
        CharacterModel actualModel = characterService.saveCharacter(characterModel);

        //then
        assertNotNull(actualModel);
        assertEquals("Jedi", actualModel.getName());
        assertEquals("Tatooine", actualModel.getPlanet());
        assertEquals(actualModel, expectedCharacter);
    }

    @Test
    void shouldReturnAllSavedCharacters(){
        //given
        CharacterModel characterModel1 = new CharacterModel("Jedi", "Naboo");
        CharacterModel characterModel2 = new CharacterModel("Trooper", "Tatooine");
        CharacterModel characterModel3 = new CharacterModel("Droid", "Alderaan");

        List<CharacterModel> characters = new ArrayList<>();
        characters.add(characterModel1);
        characters.add(characterModel2);
        characters.add(characterModel3);

        when(this.characterRepositoryMock.findAll()).thenReturn(characters);

        //when
        List<CharacterModel> actual = characterService.getAllCharacters();

        //then
        assertEquals(actual, characters);
        assertEquals(actual.get(0), characterModel1);
        assertEquals(actual.get(1), characterModel2);
        assertEquals(actual.get(2), characterModel3);

    }

    @Test
    void shouldReturnSavedCharacterById(){
        //given
        CharacterModel characterModel = new CharacterModel("Jedi", "Naboo");

        //Naboo
        
        long id = 1;

        Optional<CharacterModel> characterOptional = Optional.of(new CharacterModel("Jedi", "Tatooine"));

        when(this.characterRepositoryMock.findById(id)).thenReturn(characterOptional);

        //when
        CharacterModel actualModel = characterService.getCharacterById(1);

        
        //then
        assertEquals(actualModel, characterModel);
    }

    @Test
    void shouldReturnUpdatedCharacter(){
        //given


        //when


        //then

    }

}
