package com.crm.crm_spring.service.impl;

import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.Product;
import com.crm.crm_spring.repository.ProductRepository;
import com.crm.crm_spring.service.OrderService;
import com.crm.crm_spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // logger pour console
    Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    // récupération des méthodes via le product qui extend de jpa
    private final ProductRepository productRepository;

    // debut de la déclaration des methodes implémenté de l'interface
    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll(Sort.by("priceHt").ascending());
    }

    @Override
    public Product getById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new UnknownResourceException("No Product found for the given ID"));
    }

    @Override
    public Product create(Product product) {
        log.debug("Attempting to save product in DB...");
        return this.productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product productToDelete = this.getById(id);
        if (this.canDeleteProduct(productToDelete)) {
            this.productRepository.delete(productToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given product still has orders.");
        }
    }

    @Override
    public Product update(Product product) {
        log.debug("Attempting to update product {}", product.getId());
        Product existingProduct = this.getById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setType(product.getType());
        existingProduct.setPriceHt(product.getPriceHt());
        existingProduct.setSurface(product.getSurface());
        existingProduct.setPhotoUrl(product.getPhotoUrl());
        return this.productRepository.save(existingProduct);
    }

    private boolean canDeleteProduct(Product product) {
        return (null == product.getOrder() || product.getOrder().isEmpty());
    }
}
