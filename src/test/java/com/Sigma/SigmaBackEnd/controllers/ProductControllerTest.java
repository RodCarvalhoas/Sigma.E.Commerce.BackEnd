package com.Sigma.SigmaBackEnd.controllers;

import com.Sigma.SigmaBackEnd.dtos.ProductDTO;
import com.Sigma.SigmaBackEnd.excepitons.ObjectNotFoundException;
import com.Sigma.SigmaBackEnd.model.Category;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ProductControllerTest {

    public static final UUID ID         = new UUID(0x123456789ABCDEF1L, 0x0123456789ABCDEFL);
    public static final UUID ID_PRODUCT1 = new UUID(0x123456789ABCDEF2L, 0x0123456789ABCDEF2L);
    public static final UUID ID_PRODUCT2 = new UUID(0x123456789ABCDEF3L, 0x0123456789ABCDEF3L);

    private Category category = new Category(ID, "PERIFERICOS");

    private Product product1 = new Product(ID_PRODUCT1, "Fone", 200, "fone.img", 5, category, null);
    private Product product2 = new Product(ID_PRODUCT2, "Teclado", 100, "teclado.img", 3, category, null);

    List<Product> productList = Arrays.asList(product1, product2);

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void whenFindAllThenReturnAnListOfProducts(){
        Mockito.when(productService.findAll()).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> productDTOList = productController.findAll();

        Assertions.assertNotNull(productDTOList);
        Assertions.assertEquals(ResponseEntity.class, productDTOList.getClass());
        Assertions.assertEquals(HttpStatus.OK, productDTOList.getStatusCode());
        Assertions.assertEquals(2, productDTOList.getBody().size());
        Assertions.assertEquals(product1.getName(), productDTOList.getBody().get(0).getName());
        Assertions.assertEquals(product1.getPrice(), productDTOList.getBody().get(0).getPrice());
        Assertions.assertEquals(product1.getImg(), productDTOList.getBody().get(0).getImg());
        Assertions.assertEquals(product1.getQuantity(), productDTOList.getBody().get(0).getQuantity());
        Assertions.assertEquals(product1.getCategory().getName(), productDTOList.getBody().get(0).getCategoryName());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty(){
        Mockito.when(productService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductDTO>> productDTOList = productController.findAll();

        Assertions.assertNotNull(productDTOList);
        Assertions.assertEquals(ResponseEntity.class, productDTOList.getClass());
        Assertions.assertEquals(HttpStatus.OK, productDTOList.getStatusCode());
        Assertions.assertEquals(0, productDTOList.getBody().size());
    }

    @Test
    void whenFindByNameThenReturnProduct(){
        Mockito.when(productService.findByName(Mockito.anyString())).thenReturn(product1);

        ResponseEntity<Product> productResponseEntity = productController.findByName("Fone");

        Assertions.assertNotNull(productResponseEntity);
        Assertions.assertEquals(ResponseEntity.class, productResponseEntity.getClass());
        Assertions.assertEquals(HttpStatus.OK, productResponseEntity.getStatusCode());
        Assertions.assertEquals(product1, productResponseEntity.getBody());
    }

    @Test
    void whenFindNonExistingProductByNameThenThrowObjectNotFoundException() {
        Mockito.when(productService.findByName(Mockito.anyString())).thenThrow(ObjectNotFoundException.class);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            productController.findByName("NomeProdutoInexistente");
        });
    }

    @Test
    void whenFindAllByCategoryThenReturnListOfProducts(){
        Mockito.when(productService.findAllByCategory(Mockito.anyString())).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> productDTOList = productController.findAllByCategory("PERIFERICOS");

        Assertions.assertNotNull(productDTOList);
        Assertions.assertEquals(ResponseEntity.class, productDTOList.getClass());
        Assertions.assertEquals(HttpStatus.OK, productDTOList.getStatusCode());
        Assertions.assertEquals(2, productDTOList.getBody().size());
        Assertions.assertEquals(product1.getName(), productDTOList.getBody().get(0).getName());
    }

    @Test
    void whenFindAllByNonExistingCategoryThenThrowObjectNotFoundException() {
        Mockito.when(productService.findAllByCategory(Mockito.anyString())).thenThrow(ObjectNotFoundException.class);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            productController.findAllByCategory("CategoriaInexistente");
        });
    }

    @Test
    void whenCreateProductThenReturnCreatedStatusWithLocationHeader(){
        ProductDTO newProductDTO = new ProductDTO("Monitor", 300, "monitor.img", "PERIFERICOS", 4);

        Mockito.doAnswer(invocation -> {
            Product createdProduct = invocation.getArgument(0);
            createdProduct.setId(ID_PRODUCT1);
            return null;
        }).when(productService).create(Mockito.any(Product.class), Mockito.anyString());

        ResponseEntity<ProductDTO> responseEntity = productController.create(newProductDTO);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verificação do header
        HttpHeaders headers = responseEntity.getHeaders();
        Assertions.assertNotNull(headers);
        Assertions.assertTrue(headers.containsKey(HttpHeaders.LOCATION));

        String locationUri = headers.getFirst(HttpHeaders.LOCATION);
        Assertions.assertNotNull(locationUri);

        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        URI expectedUri = uriBuilder.path("/{id}").buildAndExpand(ID_PRODUCT1).toUri();
        Assertions.assertEquals(expectedUri.toString(), locationUri);
    }

    @Test
    void whenDeleteProductThenReturnNoContent(){
        Mockito.doNothing().when(productService).delete(Mockito.any(UUID.class));

        ResponseEntity<Void> responseEntity = productController.delete(ID_PRODUCT1);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void whenDeleteNonExistingProductThenThrowObjectNotFoundException() {
        Mockito.doThrow(ObjectNotFoundException.class).when(productService).delete(Mockito.any(UUID.class));

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            productController.delete(UUID.randomUUID());
        });
    }



}
