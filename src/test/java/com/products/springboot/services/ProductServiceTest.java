package com.products.springboot.services;

import com.products.springboot.dto.ProductRecordDto;
import com.products.springboot.exceptions.ProductNotFoundException;
import com.products.springboot.models.ProductModel;
import com.products.springboot.repositories.ProductRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        ProductModel product = productService.findById(productID);
        //Assert
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Refrigerator");
        assertThat(product.getValue()).isEqualTo(new BigDecimal("1200"));
        assertThat(product.getIdProduct()).isEqualTo(productID);
    }

    @Test
    public void shouldUpdateProductById(){
        //Arrange
        ProductRecordDto newProduct = new ProductRecordDto("Refrigerator", new BigDecimal("1200"));
        ProductModel product = productService.saveProduct(newProduct);

        //Act
        ProductRecordDto result = new ProductRecordDto("MackBook Air", new BigDecimal("999"));
        Optional<ProductModel> productModelUpdated = productService.updateProductById(product.getIdProduct(), result);

        //Assert
        assertThat(productModelUpdated).isPresent();
        assertThat(productModelUpdated.get().getName()).isEqualTo("MackBook Air");
        assertThat(productModelUpdated.get().getValue()).isEqualTo(new BigDecimal("999"));
    }
    @Test
    public void shouldDeleteProduct(){
        //Arrange
        ProductRecordDto newProduct = new ProductRecordDto("Refrigerator", new BigDecimal("1200"));
        ProductModel product = productService.saveProduct(newProduct);
        assertThat(productService.findById(product.getIdProduct())).isNotNull();
        //Act
        productService.deleteProduct(product);

        //Assert

        assertThatThrownBy(() -> productService.findById(product.getIdProduct()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found!");
    }

}
