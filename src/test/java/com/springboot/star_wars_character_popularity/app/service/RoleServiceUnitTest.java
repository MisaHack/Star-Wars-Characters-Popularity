package com.springboot.star_wars_character_popularity.app.service;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import com.springboot.star_wars_character_popularity.app.repository.RoleRepository;
import com.springboot.star_wars_character_popularity.app.service.serviceImpl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.management.relation.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleServiceUnitTest {

    private RoleServiceImpl roleService;

    private RoleRepository roleRepositoryMock;

    @BeforeEach
    void setup(){
        this.roleRepositoryMock = mock(RoleRepository.class);

        this.roleService = new RoleServiceImpl(roleRepositoryMock);
    }

    @Test
    void  shouldReturnSavedRole(){
        //given
        RoleModel roleModel = new RoleModel("Admin");

        when(this.roleRepositoryMock.save(roleModel)).thenReturn(new RoleModel("Admin"));

        RoleModel expectedRole = new RoleModel("Admin");

        //when
        RoleModel actualModel = roleService.saveRole(roleModel);

        //then
        assertNotNull(actualModel);
        assertEquals("Admin", actualModel.getRoleName());
        assertEquals(actualModel, expectedRole);
        verify(roleRepositoryMock).save(roleModel);
    }

    @Test
    void shouldReturnAllSavedRoles(){
        //given
        RoleModel roleModel1 = new RoleModel("Admin");
        RoleModel roleModel2 = new RoleModel("User");

        List<RoleModel> roles = new ArrayList<>();
        roles.add(roleModel1);
        roles.add(roleModel2);

        when(this.roleRepositoryMock.findAll()).thenReturn(roles);

        //when
        List<RoleModel> actual = roleService.getAllRoles();

        //then
        assertEquals(actual, roles);
        assertEquals(actual.get(0), roleModel1);
        assertEquals(actual.get(1), roleModel2);
        verify(roleRepositoryMock).findAll();
    }

    @Test
    void shouldReturnSavedRoleById(){
        //given
        RoleModel roleModel = new RoleModel("Admin");

        long id = 1;

        Optional<RoleModel> roleOptional = Optional.of(new RoleModel("Admin"));

        when(this.roleRepositoryMock.findById(id)).thenReturn(roleOptional);

        //when
        RoleModel actualModel = roleService.getRolesById(1);

        //then
        assertEquals(actualModel, roleModel);
        verify(roleRepositoryMock).findById(id);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenNoRoleAvailable(){
        //given
        long id = 1;

        when(this.roleRepositoryMock.findById(id)).thenThrow(ResourceNotFoundException.class);

        //when
        Executable executable = () -> roleService.getRolesById(1);

        //then
        assertThrows(ResourceNotFoundException.class, executable);
    }


}
