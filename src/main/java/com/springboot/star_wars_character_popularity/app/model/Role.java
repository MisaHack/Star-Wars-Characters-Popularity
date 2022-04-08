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
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private long id;

    @Column(name = "roleName")
    private String roleName;

    public Role(String roleName){
        this.roleName = roleName;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Role roleModel = (Role) o;
        return getId() == roleModel.getId() && getRoleName().equals(roleModel.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }
}
