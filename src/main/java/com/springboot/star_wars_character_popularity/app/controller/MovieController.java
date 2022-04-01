package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.MovieModel;
import com.springboot.star_wars_character_popularity.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    public MovieController(MovieService movieService){
       this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieModel> saveMovie(@RequestBody MovieModel movie){
        return new ResponseEntity<MovieModel>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MovieModel> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> getMovieById(@PathVariable("id") long movie_id){
        return new ResponseEntity<MovieModel>(movieService.getMovieById(movie_id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieModel> updateMovie(@PathVariable("id") long id, @RequestBody MovieModel movie){
        return new ResponseEntity<MovieModel>(movieService.updateMovie(movie, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") long id){

        movieService.deleteMovie(id);

        return new ResponseEntity<String>("Movie deleted successfully !", HttpStatus.OK);
    }
}
