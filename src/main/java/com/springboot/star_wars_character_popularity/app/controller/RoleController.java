package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.Role;
import com.springboot.star_wars_character_popularity.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<Role>(roleService.saveRole(role), HttpStatus.OK);
    }

    @GetMapping
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") long role_id){
        return new ResponseEntity<Role>(roleService.getRolesById(role_id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role){
        return new ResponseEntity<Role>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") long id){

        roleService.deleteRole(id);

        return new ResponseEntity<String>("Role deleted successfully !", HttpStatus.OK);
    }
}
