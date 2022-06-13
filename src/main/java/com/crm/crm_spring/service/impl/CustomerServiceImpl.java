package com.crm.crm_spring.service.impl;

import com.crm.crm_spring.exception.InvalidOrderStatusException;
import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.Customer;
import com.crm.crm_spring.repository.CustomerRepository;
import com.crm.crm_spring.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    // logger pour console
    Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // récupération des méthodes via le customer qui extend de jpa
    private final CustomerRepository customerRepository;

    // debut de la déclaration des methodes implémenté de l'interface
    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll(Sort.by("lastname").ascending());
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new UnknownResourceException("No customer found for the given ID"));
    }

    @Override
    public Customer create(Customer customer) {
        log.debug("Attempting to save customer in DB...");
        return this.customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        Customer customerToDelete = this.getById(id);
        if (this.canDeleteCustomer(customerToDelete)) {
            this.customerRepository.delete(customerToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given customer still has orders.");
        }
    }

    @Override
    public Customer update(Customer customer) {
        log.debug("Attempting to update customer {}", customer.getId());
        Customer existingCustomer = this.getById(customer.getId());
        existingCustomer.setLastname(customer.getLastname());
        existingCustomer.setFirstname(customer.getFirstname());
        existingCustomer.setMail(customer.getMail());
        existingCustomer.setMobile(customer.getMobile());
        existingCustomer.setActive(customer.getActive());
        existingCustomer.setStreetNumber(customer.getStreetNumber());
        existingCustomer.setStreetName(customer.getStreetName());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setZipcode(customer.getZipcode());
        existingCustomer.setNotes(customer.getNotes());
//        existingCustomer.setDealer(customer.getDealer());
        return this.customerRepository.save(existingCustomer);
    }

    private boolean canDeleteCustomer(Customer customer) {
        return (null == customer.getOrder() || customer.getOrder().isEmpty());
    }

    @Override
    public Customer patchStatus(Integer customerId, String active) {
        log.debug("attempting to patch customer {} with status {}...", customerId, active);
        if (active != null && !active.equals("")) {
            Customer existingOrder = this.getById(customerId);
            existingOrder.setActive(true);
            return customerRepository.save(existingOrder);
        } else {
            throw new InvalidOrderStatusException();
        }
    }
}
