package com.springboot.star_wars_character_popularity.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planetId")
    public long id;

    @Column(name = "planetName")
    public String name;

    public Planet(String name){
       this.name = name;
    }
}
