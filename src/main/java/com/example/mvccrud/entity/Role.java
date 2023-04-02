package com.example.mvccrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @ManyToMany(mappedBy = "roles")
    private List<User> user = new ArrayList<>();
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

}
