package com.springboot.star_wars_character_popularity.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<RoleModel> roles = new ArrayList<>();

}
