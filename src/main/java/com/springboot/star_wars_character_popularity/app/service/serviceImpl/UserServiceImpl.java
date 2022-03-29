package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.RoleModel;
import com.springboot.star_wars_character_popularity.app.model.UserModel;
import com.springboot.star_wars_character_popularity.app.repository.UserRepository;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel getUserById(long id) {
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isPresent()){
           return user.get();
        }
        else{
            throw new ResourceNotFoundException("User","id",id);
        }
    }

    @Override
    public UserModel updateUser(UserModel user, long id) {

        UserModel existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        existingUser.setUserName(user.getUserName());

        userRepository.save(existingUser);

        return existingUser;
    }

    @Override
    public void deleteUser(long id) {

        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        userRepository.deleteById(id);
    }
}
