package com.springboot.star_wars_character_popularity.app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="Character")
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "characterId")
    public long id;

    @Column(name = "roleName")
    public String name;

    @Column(name = "votersCount")
    public long count;

    @Column(name = "planet")
    public String planet;

    @ManyToMany(fetch = FetchType.LAZY)
    public Set<MovieModel> movies = new HashSet<>();

}
