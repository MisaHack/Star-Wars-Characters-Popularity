package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Character;
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
        Character characterModel = new Character("Jedi", "Naboo");

        when(this.characterRepositoryMock.save(characterModel)).thenReturn(new Character("Jedi", "Tatooine"));

        Character expectedCharacter = new Character("Jedi", "Naboo");

        //when
        Character actualModel = characterService.saveCharacter(characterModel);

        //then
        assertNotNull(actualModel);
        assertEquals("Jedi", actualModel.getName());
        assertEquals("Tatooine", actualModel.getHomeworld());
        assertEquals(actualModel, expectedCharacter);
        verify(characterRepositoryMock).save(characterModel);
    }

    @Test
    void shouldReturnAllSavedCharacters(){
        //given
        Character characterModel1 = new Character("Jedi", "Naboo");
        Character characterModel2 = new Character("Trooper", "Tatooine");
        Character characterModel3 = new Character("Droid", "Alderaan");

        List<Character> characters = new ArrayList<>();
        characters.add(characterModel1);
        characters.add(characterModel2);
        characters.add(characterModel3);

        when(this.characterRepositoryMock.findAll()).thenReturn(characters);

        //when
        List<Character> actual = characterService.getAllCharacters();

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
        Character characterModel = new Character("Jedi", "Naboo");
        
        long id = 1;

        Optional<Character> characterOptional = Optional.of(new Character("Jedi", "Tatooine"));

        when(this.characterRepositoryMock.findById(id)).thenReturn(characterOptional);

        //when
        Character actualModel = characterService.getCharacterById(1);

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
        Character characterModel = new Character("Jedi", "Naboo");

        long id = 1;

        Optional<Character> characterOptional = Optional.of(new Character("Jedi", "Tatooine"));

        when(this.characterRepositoryMock.findById(id)).thenReturn(characterOptional);

        //when
        Character actualModel = characterService.updateCharacter(characterModel, id);

        //then
        assertEquals("Jedi", actualModel.getName());
        assertEquals("Naboo", actualModel.getHomeworld());
    }
}
