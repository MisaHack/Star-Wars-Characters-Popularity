package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.User;
import com.springboot.star_wars_character_popularity.app.repository.UserRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {

    private UserServiceImpl userService;

    private UserRepository userRepositoryMock;

    @BeforeEach
    void setup(){
        this.userRepositoryMock = mock(UserRepository.class);

        this.userService = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    void shouldReturnSavedUser(){
        //given
        User userModel = new User("Misa", "misa@axiom.com");

        when(this.userRepositoryMock.save(userModel)).thenReturn(new User("Misa", "misa@axiom.com"));

        User expectedUser = new User("Misa", "misa@axiom.com");

        //when
        User actualModel = userService.saveUser(userModel);

        //then
        assertNotNull(actualModel);
        assertEquals("Misa", actualModel.getUserName());
        assertEquals("misa@axiom.com", actualModel.getEmail());
        assertEquals(actualModel, expectedUser);
        verify(userRepositoryMock).save(userModel);
    }

    @Test
    void shouldReturnAllSavedUsers(){
        //given
        User userModel1 = new User("Misa","misa@axiom.com");
        User userModel2 = new User("Stefan","stefan@axiom.com");
        User userModel3 = new User("Zoran","zoran@axiom.com");

        List<User> users = new ArrayList<>();
        users.add(userModel1);
        users.add(userModel2);
        users.add(userModel3);

        when(this.userRepositoryMock.findAll()).thenReturn(users);

        //when
        List<User> actual = userService.getAllUsers();

        //then
        assertEquals(actual, users);
        assertEquals(actual.get(0), userModel1);
        assertEquals(actual.get(1), userModel2);
        assertEquals(actual.get(2), userModel3);
        verify(userRepositoryMock).findAll();
    }

    @Test
    void shouldReturnSavedUserById(){
        //given
        User userModel = new User("Misa","misa@axiom.com");

        long id = 1;

        Optional<User> userOptional = Optional.of(new User("Misa","misa@axiom.com"));

        when(this.userRepositoryMock.findById(id)).thenReturn(userOptional);

        //when
        User actualModel = userService.getUserById(1);

        //then
        assertEquals(actualModel, userModel);
        verify(userRepositoryMock).findById(id);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenNoUserAvailable(){
        //given
        long id = 1;

        when(this.userRepositoryMock.findById(id)).thenThrow(ResourceNotFoundException.class);

        //when
        Executable executable = () -> userService.getUserById(1);

        //then
        assertThrows(ResourceNotFoundException.class, executable);
    }
}
