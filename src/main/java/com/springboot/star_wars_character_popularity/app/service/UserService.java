package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import com.springboot.star_wars_character_popularity.app.model.UserModel;

import java.util.List;

public interface UserService {
   UserModel saveUser(UserModel user);
   List<RoleModel> getAllRoles();
   RoleModel getRoleById(long id);
   RoleModel updateRole(RoleModel role, long id);
   void deleteRole(long id);
}
