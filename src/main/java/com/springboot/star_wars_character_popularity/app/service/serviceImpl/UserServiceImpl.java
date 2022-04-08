package com.springboot.star_wars_character_popularity.app.service.serviceImpl;

import com.springboot.star_wars_character_popularity.app.exception.ResourceNotFoundException;
import com.springboot.star_wars_character_popularity.app.model.Role;
import com.springboot.star_wars_character_popularity.app.model.User;
import com.springboot.star_wars_character_popularity.app.repository.UserRepository;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
           return user.get();
        }
        else{
            throw new ResourceNotFoundException("User","id",id);
        }
    }

    @Override
    public User updateUser(User user, long id) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        existingUser.setUserName(user.getUserName());

        userRepository.save(existingUser);

        return existingUser;
    }

    @Override
    public void deleteUser(long id) {

        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user = userRepository.findByEmail(username);
       if(user == null){
          throw new UsernameNotFoundException("Invalid username or password.");
       }

       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){

       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

    }
}
