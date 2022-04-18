package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.help.CharacterPage;
import com.springboot.star_wars_character_popularity.app.help.PlanetPage;
import com.springboot.star_wars_character_popularity.app.model.Character;
import com.springboot.star_wars_character_popularity.app.model.Planet;
import com.springboot.star_wars_character_popularity.app.repository.CharacterRepository;
import com.springboot.star_wars_character_popularity.app.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    public String planetsUri = "https://swapi.dev/api/planets/";

    private CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository){
        this.characterRepository=characterRepository;
    }

    @Override
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character getCharacterById(long id) {

        Optional<Character> character = characterRepository.findById(id);

        if(character.isPresent()){
            return character.get();
        }
        else{
            throw new ResourceNotFoundException("Character","id",id);
        }
    }

    @Override
    public Character updateCharacter(Character character, long id) {

        Character existingCharacter = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","id",id));

        existingCharacter.setName(character.getName());

        characterRepository.save(existingCharacter);

        return existingCharacter;
    }

    @Override
    public void deleteCharacter(long id) {

        characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","id",id));

        characterRepository.deleteById(id);
    }

    @Override
    public void fetchCharactersOptimized() {

        List<Planet> allAvailablePlanets = getListOfAllPlanets();

        RestTemplate restTemplateCharacters = new RestTemplate();
        CharacterPage characterPage = restTemplateCharacters.getForObject("https://swapi.dev/api/people", CharacterPage.class);

        List<Character> charactersPerPage = characterPage.getResults();

        boolean noMoreCharacterPages = false;

        do {

            if(characterPage.getNext() == null)
                noMoreCharacterPages = true;

            for (int i = 0; i < charactersPerPage.size(); i++) {

                Character character1 = charactersPerPage.get(i);

                int uriLength = planetsUri.length();

                String homeworldURI = character1.getHomeworld();
                int homeworldIndex = Integer.parseInt(homeworldURI.substring(uriLength , homeworldURI.length()-1));

                character1.setHomeworld(allAvailablePlanets.get(homeworldIndex-1).getName());

                characterRepository.save(character1);
            }

            if(!noMoreCharacterPages) {
                characterPage = restTemplateCharacters.getForObject(characterPage.getNext(), CharacterPage.class);
                charactersPerPage = characterPage.getResults();
            }

        }while(!noMoreCharacterPages);
    }

    public List<Planet> getListOfAllPlanets(){

        RestTemplate restTemplatePlanet = new RestTemplate();
        PlanetPage planetPage = restTemplatePlanet.getForObject(planetsUri , PlanetPage.class);

        List<Planet> planetsPerPage = planetPage.getResults();

        List<Planet> allAvailablePlanets = new ArrayList<>();

        boolean noMorePlanetPages = false;

        do {

            if(planetPage.getNext() == null)
                noMorePlanetPages = true;

            allAvailablePlanets.addAll(planetsPerPage);

            if(!noMorePlanetPages) {
                planetPage = restTemplatePlanet.getForObject(planetPage.getNext(), PlanetPage.class);
                planetsPerPage = planetPage.getResults();
            }

        }while(!noMorePlanetPages);

        return allAvailablePlanets;
    }
}
