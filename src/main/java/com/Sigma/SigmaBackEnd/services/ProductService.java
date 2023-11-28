package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.excepitons.ObjectNotFoundException;
import com.Sigma.SigmaBackEnd.model.Category;
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

    @Autowired
    private CategoryService categoryService;

    public Product findById(UUID id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException("Produto com id: " + id + " não encontrado"));
    }

    public Product findByName(String productName){
        Optional<Product> product = productRepository.findByName(productName);
        return product.orElseThrow(() -> new ObjectNotFoundException("Produto com o nome: " + productName + " não encontrado"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(String categoryName){
        return productRepository.findAllByCategory(categoryName);
    }

    public Product create(Product product, String categoryName){
        Category cat = categoryService.findByName(categoryName);
        product.setCategory(cat);
        return productRepository.save(product);
    }

    public Product update(Product product, String categoryName, UUID id){
        Product prod = findById(id);
        Category cat = categoryService.findByName(categoryName);
        product.setCategory(cat);
        product.setId(prod.getId());
        return productRepository.save(product);
    }

    public void delete(UUID id){
        Product product = findById(id);
        productRepository.deleteById(product.getId());
    }

}
