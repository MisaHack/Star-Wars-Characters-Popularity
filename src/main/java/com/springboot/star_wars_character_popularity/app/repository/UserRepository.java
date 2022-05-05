package com.springboot.star_wars_character_popularity.app.repository;

import com.springboot.star_wars_character_popularity.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
   User findByUserName(String userName);
}
