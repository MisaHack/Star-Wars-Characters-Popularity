package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.dto.RoleToUserForm;
import com.springboot.star_wars_character_popularity.app.model.Role;
import com.springboot.star_wars_character_popularity.app.model.User;
import com.springboot.star_wars_character_popularity.app.service.RoleService;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    private UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long user_id){
        return new ResponseEntity<User>(userService.getUserById(user_id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){

        userService.deleteUser(id);

        return new ResponseEntity<String>("User deleted successfully !", HttpStatus.OK);
    }

    @GetMapping("/show-registration-form")
    public ModelAndView showRegistrationForm(Model model){

       model.addAttribute("user", new User());

       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("users/registration");

       return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") User registration){

        userService.saveUser(registration);

        userService.addRoleToUser(registration.getUserName(), "ROLE_USER");

        String userName = registration.getUserName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/api/user/show-registration-form?success&name=" + userName);

        return modelAndView;
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<Role>(userService.saveRole(role), HttpStatus.OK);
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/addToUser")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }
}
