package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


}
