package com.example.armstorage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="locale")
    private String locale;

    @JsonIgnore
    @OneToMany
    private Set<UserEntity> users;
}