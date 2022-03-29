package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.MovieModel;

import java.util.List;

public interface MovieService {
    MovieModel saveMovie(MovieModel movie);
    List<MovieModel> getAllMovies();
    MovieModel getAllMoviesById(long id);
    MovieModel updateMovie(MovieModel movie, long id);
    void deleteMovie(long id);
}
