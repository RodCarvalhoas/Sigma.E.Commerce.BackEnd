package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.dtos.CategoryDTO;
import com.Sigma.SigmaBackEnd.model.Category;
import com.Sigma.SigmaBackEnd.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CategoryControllerTest {

    private static final UUID ID_CATEGORY_1 = UUID.randomUUID();
    private static final UUID ID_CATEGORY_2 = UUID.randomUUID();

    private Category category1;
    private Category category2;
    private CategoryDTO categoryDTO;

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category(ID_CATEGORY_1, "Category 1");
        category2 = new Category(ID_CATEGORY_2, "Category 2");
        categoryDTO = new CategoryDTO(category1);
    }

    @Test
    void whenFindAllThenReturnListOfCategories() {
        List<Category> categories = Arrays.asList(category1, category2);
        Mockito.when(categoryService.findAll()).thenReturn(categories);

        ResponseEntity<List<CategoryDTO>> response = categoryController.findAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void whenFindByIdThenReturnCategory() {
        Mockito.when(categoryService.findById(ID_CATEGORY_1)).thenReturn(category1);

        ResponseEntity<CategoryDTO> response = categoryController.findById(ID_CATEGORY_1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(category1.getName(), response.getBody().getName());
        Assertions.assertEquals(category1.getProducts(), response.getBody().getProducts());
    }

    @Test
    void whenDeleteCategoryThenReturnNoContent() {
        ResponseEntity<Void> response = categoryController.delete(ID_CATEGORY_1);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}