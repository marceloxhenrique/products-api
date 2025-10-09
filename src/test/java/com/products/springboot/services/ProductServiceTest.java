package com.products.springboot.services;

import com.products.springboot.dto.ProductRecordDto;
import com.products.springboot.models.ProductModel;
import com.products.springboot.repositories.ProductRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    private final ProductRepositoryInMemory repository = new ProductRepositoryInMemory();
    private final ProductService productService = new ProductService(repository);

    @Test
    public void shouldSaveProduct(){
        //Arrange
        ProductRecordDto newProduct = new ProductRecordDto("Refrigerator", new BigDecimal("1200"));

        //Act
        ProductModel result  = productService.saveProduct(newProduct);

        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Refrigerator");
        assertThat(result.getIdProduct()).isNotNull();

    }
    @Test
    public void shouldReturnAllProducts(){
        //Arrange
        ProductRecordDto newProduct = new ProductRecordDto("Refrigerator", new BigDecimal("1200"));
        ProductRecordDto newProduct1 = new ProductRecordDto("Microwave", new BigDecimal("200"));

        //Act
        productService.saveProduct(newProduct);
        productService.saveProduct(newProduct1);

        //Assert
        List<ProductModel> productList = productService.findAllProducts();
        assertThat(productList).hasSize(2);
        assertThat(productList.get(0).getName()).isEqualTo("Refrigerator");
        assertThat(productList.get(1).getName()).isEqualTo("Microwave");
    }

    @Test
    public void shouldFindProductById(){
        //Arrange
        ProductRecordDto newProduct = new ProductRecordDto("Refrigerator", new BigDecimal("1200"));

        //Act
        ProductModel result = productService.saveProduct(newProduct);
        UUID productID = result.getIdProduct();
        Optional<ProductModel> product = productService.findById(productID);

        //Assert
        assertThat(product).isPresent();
        assertThat(product.get().getName()).isEqualTo("Refrigerator");
        assertThat(product.get().getValue()).isEqualTo(new BigDecimal("1200"));
        assertThat(product.get().getIdProduct()).isEqualTo(productID);
    }
}
