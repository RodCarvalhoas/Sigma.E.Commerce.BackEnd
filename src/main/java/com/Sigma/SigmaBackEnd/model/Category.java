package com.Sigma.SigmaBackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> product = new ArrayList<>();

    public Category(String id, String name){
        this.id = id;
        this.name = name;
    }
}
