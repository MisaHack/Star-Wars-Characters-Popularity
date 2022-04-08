package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(long id);
    Movie updateMovie(Movie movie, long id);
    void deleteMovie(long id);
}
