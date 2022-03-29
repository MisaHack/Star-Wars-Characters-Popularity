package com.springboot.star_wars_character_popularity.app.repository;

import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

}
