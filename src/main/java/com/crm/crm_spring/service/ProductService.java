package com.crm.crm_spring.service;

import com.crm.crm_spring.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Get all products
     * @return all products
     */
    List<Product> getAll();

    /**
     * Get product by id
     * @param id the id
     * @return product with the given id
     */
    Product getById(Integer id);

    /**
     * Create product
     * @param product the product to create
     * @return the created product
     */
    Product create(Product product);

    /**
     * Delete Product
     * @param id the Product id
     */
    void delete(Integer id);

    /**
     * Update Product
     * @param product the Product
     * @return the updated Product
     */
    Product update(Product product);

}
