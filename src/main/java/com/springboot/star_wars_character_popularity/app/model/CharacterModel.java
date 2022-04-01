package com.springboot.star_wars_character_popularity.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public CharacterModel(String name, String planet){
        this.name=name;
        this.planet=planet;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "character_movies",
            joinColumns = @JoinColumn(name = "characterId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    public Set<MovieModel> movies = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CharacterModel that = (CharacterModel) o;
        return getId() == that.getId() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
