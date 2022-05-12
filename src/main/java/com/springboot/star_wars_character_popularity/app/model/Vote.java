package com.springboot.star_wars_character_popularity.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voteId")
    public long id;

    @Min(1)
    @Max(10)
    @Column(name = "count")
    public int count;

    @Size(min=1, max=120)
    @Column(name = "comment")
    public String comment;

    //@Transient
    //private List<MultipartFile> icons = new ArrayList<MultipartFile>();
    ///@Transient
    //private List<String> removeImages = new ArrayList<String>();

    public Vote(int count, String comment){
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
