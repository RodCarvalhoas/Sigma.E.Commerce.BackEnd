package com.Sigma.SigmaBackEnd.dtos;

import com.Sigma.SigmaBackEnd.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private Number price;
    private String img;
    private String categoryName;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.img = product.getImg();
        this.categoryName = product.getCategory().getName();
    }
}
