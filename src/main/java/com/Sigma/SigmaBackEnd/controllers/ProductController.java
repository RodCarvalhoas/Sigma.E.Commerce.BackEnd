package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.dtos.ProductDTO;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping(value = "/{productName}")
    public ResponseEntity<ProductDTO> findById(@PathVariable String productName){
        Product product = productService.findByName(productName);
        ProductDTO productDTO = new ProductDTO(product);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> productsListAllByCategory = productService.findAll();
        List<ProductDTO> productDTOListAllByCategory = productsListAllByCategory
                .stream()
                .map(product -> new ProductDTO(product)).collect(Collectors.toList());
        return ResponseEntity.ok().body(productDTOListAllByCategory);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllByCategory(@RequestParam String categoryName){
        List<Product> productsListAllByCategory = productService.findAllByCategory(categoryName);
        List<ProductDTO> productDTOListAllByCategory = productsListAllByCategory
                .stream()
                .map(product -> new ProductDTO(product)).collect(Collectors.toList());
        return ResponseEntity.ok().body(productDTOListAllByCategory);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        productService.create(product, productDTO.getCategoryName());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
