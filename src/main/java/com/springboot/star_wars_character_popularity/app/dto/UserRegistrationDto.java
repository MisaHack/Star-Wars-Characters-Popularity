package com.springboot.star_wars_character_popularity.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDto {

    private String userName;
    private String email;
    private String password;

}
