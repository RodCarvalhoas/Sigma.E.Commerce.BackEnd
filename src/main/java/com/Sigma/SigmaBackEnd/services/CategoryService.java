package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.excepitons.ObjectNotFoundException;
import com.Sigma.SigmaBackEnd.model.Category;
import com.Sigma.SigmaBackEnd.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(UUID id){
         Optional<Category> cat = categoryRepository.findById(id);
         return cat.orElseThrow(() -> new ObjectNotFoundException("Categoria com o id: " + id + " não existe"));
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
