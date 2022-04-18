package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
   User saveUser(User user);
   List<User> getAllUsers();
   User getUserById(long id);
   User updateUser(User role, long id);
   void deleteUser(long id);
}
