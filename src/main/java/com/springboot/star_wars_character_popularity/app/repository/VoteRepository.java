package com.springboot.star_wars_character_popularity.app.repository;

import com.springboot.star_wars_character_popularity.app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
