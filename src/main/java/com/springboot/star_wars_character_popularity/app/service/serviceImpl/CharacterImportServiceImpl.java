package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.help.CharacterPage;
import com.springboot.star_wars_character_popularity.app.model.Character;
import com.springboot.star_wars_character_popularity.app.model.Planet;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.CharacterImportService;
import com.springboot.star_wars_character_popularity.app.service.PlanetImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterImportServiceImpl implements CharacterImportService {

    @Value("${planet.uri}")
    String planetsUri;

    @Value("${people.uri}")
    String peopleUri;

    private final PlanetImportService planetImportServiceImpl;
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate;

    @Override
    public void fetchCharacters() {

        List<Planet> allAvailablePlanets = planetImportServiceImpl.fetchPlanets();

        String url = peopleUri;

        do {

            CharacterPage characterPage = restTemplate.getForObject(url, CharacterPage.class);
            List<Character> charactersPerPage = characterPage.getResults();
            url = characterPage.getNext();

            for (Character character : charactersPerPage) {

                int uriLength = planetsUri.length();

                String homeworldURI = character.getHomeworld();
                int homeworldIndex = Integer.parseInt(homeworldURI.substring(uriLength , homeworldURI.length()-1));

                character.setHomeworld(allAvailablePlanets.get(homeworldIndex-1).getName());

                characterRepository.save(character);
            }

        }while(url != null);
    }
}
