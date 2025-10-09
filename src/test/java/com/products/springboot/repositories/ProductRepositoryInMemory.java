package com.products.springboot.repositories;
import com.products.springboot.models.ProductModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class ProductRepositoryInMemory implements ProductRepository {
    private final Map<UUID, ProductModel> storage = new LinkedHashMap<>();
    @Override
    public ProductModel save(ProductModel product) {
        if (product.getIdProduct() == null) {
            product.setIdProduct(UUID.randomUUID());
        }
        storage.put(product.getIdProduct(), product);
        return product;
    }
    @Override
    public Optional<ProductModel> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }
    @Override
    public List<ProductModel> findAll() {
        return new ArrayList<>(storage.values());
    }
    @Override
    public void delete(ProductModel product) {
        storage.remove(product.getIdProduct());
    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends ProductModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProductModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductModel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProductModel getOne(UUID uuid) {
        return null;
    }

    @Override
    public ProductModel getById(UUID uuid) {
        return null;
    }

    @Override
    public ProductModel getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends ProductModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProductModel> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ProductModel> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ProductModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProductModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProductModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProductModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ProductModel> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<ProductModel> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProductModel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ProductModel> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ProductModel> findAll(Pageable pageable) {
        return null;
    }
}
