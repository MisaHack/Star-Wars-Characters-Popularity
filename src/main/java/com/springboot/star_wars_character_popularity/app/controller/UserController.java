package com.springboot.star_wars_character_popularity.app.controller;

import com.springboot.star_wars_character_popularity.app.model.User;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    private UserController(UserService userService){
        this.userService = userService;
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

        String userName = registration.getUserName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/api/user/show-registration-form?success&name=" + userName);

        return modelAndView;
    }
}
