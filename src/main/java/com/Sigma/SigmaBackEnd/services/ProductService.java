package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.excepitons.ObjectNotFoundException;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(UUID id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException("Produto com id: " + id + " não encontrado"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(UUID CategoryId){
        return productRepository.findAllByCategory(CategoryId);
    }

}
