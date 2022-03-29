package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.model.RoleModel;

import java.util.List;

public interface RoleService {
    RoleModel saveRole(RoleModel role);
    List<RoleModel> getAllRoles();
    RoleModel getRolesById(long id);
    RoleModel updateRole(RoleModel role, long id);
    void deleteRole(long id);
}
