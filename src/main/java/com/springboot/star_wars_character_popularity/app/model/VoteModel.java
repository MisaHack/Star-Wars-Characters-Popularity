package com.springboot.star_wars_character_popularity.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Vote")
public class VoteModel {

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

}
