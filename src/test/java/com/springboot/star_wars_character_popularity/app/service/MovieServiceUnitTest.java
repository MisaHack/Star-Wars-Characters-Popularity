package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Movie;
import com.springboot.star_wars_character_popularity.app.repository.MovieRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.MovieServiceImpl;
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

public class MovieServiceUnitTest {

    private MovieServiceImpl movieService;

    private MovieRepository movieRepositoryMock;

    @BeforeEach
    void setup(){
        this.movieRepositoryMock = mock(MovieRepository.class);

        this.movieService = new MovieServiceImpl(movieRepositoryMock);
    }

    @Test
    void shouldReturnSavedMovie(){
        //given
        Movie movieModel = new Movie("A New Hope");

        when(this.movieRepositoryMock.save(movieModel)).thenReturn(new Movie("A New Hope"));

        Movie expectedMovie =  new Movie("A New Hope");

        //when
        Movie actualModel = movieService.saveMovie(movieModel);

        //then
        assertNotNull(actualModel);
        assertEquals("A New Hope", actualModel.getName());
        assertEquals(actualModel, expectedMovie);
        verify(movieRepositoryMock).save(movieModel);
    }

    @Test
    void shouldReturnAllSavedMovies(){
        //given
        Movie movieModel1 = new Movie("A New Hope");
        Movie movieModel2 = new Movie("The Empire Strikes Back");
        Movie movieModel3 = new Movie("Return of the Jedi");

        List<Movie> movies = new ArrayList<>();
        movies.add(movieModel1);
        movies.add(movieModel2);
        movies.add(movieModel3);

        when(this.movieRepositoryMock.findAll()).thenReturn(movies);

        //when
        List<Movie> actual = movieService.getAllMovies();

        //then
        assertEquals(actual, movies);
        assertEquals(actual.get(0), movieModel1);
        assertEquals(actual.get(1), movieModel2);
        assertEquals(actual.get(2), movieModel3);
        verify(movieRepositoryMock).findAll();
    }

    @Test
    void shouldReturnSavedMoviesById(){
        //given
        Movie movieModel = new Movie("The Empire Strikes Back");

        long id = 1;

        Optional<Movie> movieOptional = Optional.of(new Movie("The Empire Strikes Back"));

        when(this.movieRepositoryMock.findById(id)).thenReturn(movieOptional);

        //when
        Movie actualModel = movieService.getMovieById(1);

        //then
        assertEquals(actualModel, movieModel);
        verify(movieRepositoryMock).findById(id);

    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenNoVideoAvailable(){
        //given
        long id = 1;

        when(this.movieRepositoryMock.findById(id)).thenThrow(ResourceNotFoundException.class);

        //when
        Executable executable = () -> movieService.getMovieById(1);

        //then
        assertThrows(ResourceNotFoundException.class, executable);
    }
}
