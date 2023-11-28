package com.Sigma.SigmaBackEnd.dtos;

import com.Sigma.SigmaBackEnd.model.Category;
import com.Sigma.SigmaBackEnd.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private List<Product> products = new ArrayList<>();

    public CategoryDTO (Category category){
        this.name = category.getName();
        this.products = category.getProducts();
    }
}
