package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.CharacterModel;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        verify(characterRepositoryMock).save(characterModel);
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
        verify(characterRepositoryMock).findAll();

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
        verify(characterRepositoryMock).findById(id);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenNoCharacterAvailable(){
        //given
        long id = 1;

        when(this.characterRepositoryMock.findById(id)).thenThrow(ResourceNotFoundException.class);

        //when
        Executable executable = () ->  characterService.getCharacterById(1);

        //then
        assertThrows(ResourceNotFoundException.class, executable);
    }

    @Test
    void shouldReturnUpdatedCharacter(){
        //given
        CharacterModel characterModel = new CharacterModel("Jedi", "Naboo");

        long id = 1;

        Optional<CharacterModel> characterOptional = Optional.of(new CharacterModel("Jedi", "Tatooine"));
        //CharacterModel characterOptional = new CharacterModel("Jedi", "Tatooine");

        when(this.characterRepositoryMock.findById(id)).thenReturn(characterOptional);

        //characterOptional.

        //when
        CharacterModel actualModel = characterService.updateCharacter(characterModel, id);

        //then
        assertEquals("Jedi", actualModel.getName());
        assertEquals("Naboo", actualModel.getPlanet());
    }
}
