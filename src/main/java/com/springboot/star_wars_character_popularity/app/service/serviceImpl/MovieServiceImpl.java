package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.MovieModel;
import com.springboot.star_wars_character_popularity.app.repository.MovieRepository;
import com.springboot.star_wars_character_popularity.app.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieModel saveMovie(MovieModel movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieModel getAllMoviesById(long id) {
        Optional<MovieModel> movie = movieRepository.findById(id);

        if(movie.isPresent()){
            return movie.get();
        }
        else {
            throw new ResourceNotFoundException("Movie","id",id);
        }
    }

    @Override
    public MovieModel updateMovie(MovieModel movie, long id) {

        MovieModel existingMovie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie","id",id));

        existingMovie.setName(movie.getName());

        movieRepository.save(existingMovie);

        return existingMovie;
    }

    @Override
    public void deleteMovie(long id) {

        movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie","id",id));

        movieRepository.deleteById(id);
    }
}
