package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.MovieModel;
import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import com.springboot.star_wars_character_popularity.app.service.MovieService;
import com.springboot.star_wars_character_popularity.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleModel> saveRole(@RequestBody RoleModel role){
        return new ResponseEntity<RoleModel>(roleService.saveRole(role), HttpStatus.OK);
    }

    @GetMapping
    public List<RoleModel> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleModel> getRoleById(@PathVariable("id") long role_id){
        return new ResponseEntity<RoleModel>(roleService.getRolesById(role_id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleModel> updateRole(@PathVariable("id") long id, @RequestBody RoleModel role){
        return new ResponseEntity<RoleModel>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") long id){

        roleService.deleteRole(id);

        return new ResponseEntity<String>("Role deleted successfully !", HttpStatus.OK);
    }
}
