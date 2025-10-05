package com.products.springboot.services;

import com.products.springboot.controllers.ProductController;
import com.products.springboot.dto.ProductRecordDto;
import com.products.springboot.models.ProductModel;
import com.products.springboot.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductModel saveProduct(ProductRecordDto productRecordDto){
        var productModel = new  ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepository.save(productModel);
    }

    @Transactional
    public List<ProductModel> findAllProducts(){
        List<ProductModel> productList = productRepository.findAll();
        if(!productList.isEmpty()){
            for(ProductModel product : productList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return productList;
    }

    @Transactional
    public Optional<ProductModel> findById(UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return  product;
        }
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Product List"));
        return  product;
    }
    @Transactional
    public Optional<ProductModel> updateProductById(UUID id, ProductRecordDto productRecordDto){
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return  product;
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return Optional.of(productRepository.save(productModel));
    }

    @Transactional
    public void deleteProduct(ProductModel productModel){
        productRepository.delete(productModel);
    }

}
