package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import com.springboot.star_wars_character_popularity.app.repository.RoleRepository;
import com.springboot.star_wars_character_popularity.app.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        return roleRepository.save(role);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleModel getRolesById(long id) {
        Optional<RoleModel> role = roleRepository.findById(id);

        if(role.isPresent()){
            return role.get();
        }
        else{
            throw new ResourceNotFoundException("Role","id",id);
        }
    }

    @Override
    public RoleModel updateRole(RoleModel role, long id) {

        RoleModel existingRole = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role","id",id));

        existingRole.setRoleName(role.getRoleName());

        roleRepository.save(existingRole);

        return existingRole;
    }

    @Override
    public void deleteRole(long id) {

       roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role","id",id));

       roleRepository.deleteById(id);
    }
}
