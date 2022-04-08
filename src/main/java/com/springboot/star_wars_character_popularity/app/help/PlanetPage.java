package com.springboot.star_wars_character_popularity.app.help;

import com.springboot.star_wars_character_popularity.app.model.Planet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetPage {

    public int count;

    public String next;

    public List<Planet> results = new ArrayList<>();
}
