package com.springboot.star_wars_character_popularity.app.util;

import com.springboot.star_wars_character_popularity.app.model.Role;
import com.springboot.star_wars_character_popularity.app.model.User;
import com.springboot.star_wars_character_popularity.app.repository.RoleRepository;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Data that will be initialized when Application starts.
 */
@Component
public class AppStartupData implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));

        User admin1 = new User();
        admin1.setUserName("admin_1");
        admin1.setEmail("admin_1@axiom.com");
        admin1.setPassword("admin1");
        userService.saveUser(admin1);
        userService.addRoleToUser("admin_1", "ROLE_ADMIN");

        User admin2 = new User();
        admin2.setUserName("admin_2");
        admin2.setEmail("admin_2@axiom.com");
        admin2.setPassword("admin2");
        userService.saveUser(admin2);
        userService.addRoleToUser("admin_2", "ROLE_ADMIN");
    }
}
