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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vote")
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "voteId")
    public long id;

    @Column(name = "count")
    public long count;

    @Column(name = "comment")
    public String comment;

    @Column(name = "icon")
    public byte [] icon;

    public Vote(long count, String comment){
        this.count = count;
        this.comment = comment;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_votes",
            joinColumns = @JoinColumn(name = "voteId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vote voteModel = (Vote) o;
        return getId() == voteModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}