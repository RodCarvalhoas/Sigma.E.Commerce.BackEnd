package com.Sigma.SigmaBackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> product = new ArrayList<>();

    public Category(UUID id, String name){
        this.id = id;
        this.name = name;
    }
}
