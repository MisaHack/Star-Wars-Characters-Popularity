package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.UserModel;

import java.util.List;

public interface UserService {
   UserModel saveUser(UserModel user);
   List<UserModel> getAllUsers();
   UserModel getUserById(long id);
   UserModel updateUser(UserModel role, long id);
   void deleteUser(long id);
}
