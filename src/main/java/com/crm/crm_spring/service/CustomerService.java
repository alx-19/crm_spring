package com.crm.crm_spring.service;

import com.crm.crm_spring.model.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * Get all customers
     * @return all customers
     */
    List<Customer> getAll();

    /**
     * Get customer by id
     * @param id the id
     * @return customer by id
     */
    Customer getById(Integer id);

    /**
     * Create customer
     * @param customer the customer to create
     * @return the created customer
     */
    Customer create(Customer customer);

    /**
     * Delete customer
     * @param id the customer id
     */
    void delete(Integer id);

    /**
     * Update customer
     * @param customer the customer
     * @return the updated customer
     */
    Customer update(Customer customer);

    /**
     * Patch an attribut of a customer
     * @param customerId the customer
     * @param active status of customer
     * @return the custumer with a statut patched
     */
    Customer patchStatus(Integer customerId, String active);

}
