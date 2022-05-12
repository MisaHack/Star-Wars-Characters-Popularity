package com.springboot.star_wars_character_popularity.app.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "vote_files")
public class VoteFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "modified_file_name")
    private String modifiedFileName;
    @Column(name = "file_extension")
    private String fileExtension;
    @Column(name = "icon_path")
    private String iconPath;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "voteId")
    private Vote vote;
}
