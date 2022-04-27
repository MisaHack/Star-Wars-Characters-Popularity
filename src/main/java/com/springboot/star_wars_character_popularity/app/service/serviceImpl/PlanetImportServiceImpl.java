package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.help.PlanetPage;
import com.springboot.star_wars_character_popularity.app.model.Planet;
import com.springboot.star_wars_character_popularity.app.service.PlanetImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetImportServiceImpl implements PlanetImportService {

    @Value("${planet.uri}")
    String planetsUri;

    private final RestTemplate restTemplate;

    @Override
    public List<Planet> fetchPlanets(){

        String url = planetsUri;
        List<Planet> allAvailablePlanets = new ArrayList<>();

        do {

            PlanetPage planetPage = restTemplate.getForObject(url , PlanetPage.class);
            allAvailablePlanets.addAll(planetPage.getResults());
            url = planetPage.getNext();

        }while(url != null);

        return allAvailablePlanets;
    }
}
