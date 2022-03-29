package com.springboot.star_wars_character_popularity.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Movie")
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieId")
    public long id;

    @Column(name = "videoName")
    public String name;


}
