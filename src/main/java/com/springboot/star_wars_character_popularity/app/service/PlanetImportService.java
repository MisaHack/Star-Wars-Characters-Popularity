package com.springboot.star_wars_character_popularity.app.service;
import com.springboot.star_wars_character_popularity.app.model.Planet;
import java.util.List;

public interface PlanetImportService {
    List<Planet> fetchPlanets();
}
