package com.springboot.star_wars_character_popularity.app.repository;

import com.springboot.star_wars_character_popularity.app.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
