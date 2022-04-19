package com.springboot.star_wars_character_popularity.app.repository;

import com.springboot.star_wars_character_popularity.app.model.VoteFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteFilesRepository extends JpaRepository<VoteFiles, Long> {
}
