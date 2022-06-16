package com.crm.crm_spring.service.impl;


import com.crm.crm_spring.exception.NotAllowedToDeleteException;
import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.Order;
import com.crm.crm_spring.repository.OrderRepository;
import com.crm.crm_spring.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    // logger pour console
    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    // récupération des méthodes via le order qui extend de jpa
    private final OrderRepository orderRepository;

    // debut de la déclaration des methodes implémenté de l'interface
    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll(Sort.by("date").ascending());
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new UnknownResourceException("No order found for the given ID"));
    }

    @Override
    public Order create(Order order) {
        log.debug("Attempting to save order in DB...");
        return this.orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        Order orderToDelete = this.getById(id);
        if (this.canDeleteOrder(orderToDelete)) {
            this.orderRepository.delete(orderToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given order still has active.");
        }
    }

    @Override
    public Order update(Order order) {
        log.debug("Attempting to update Order {}", order.getId());
        Order existingOrder = this.getById(order.getId());
        existingOrder.setReference(order.getReference());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setDate(order.getDate());
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setDealer(order.getDealer());
        existingOrder.setHouse(order.getHouse());
        return this.orderRepository.save(existingOrder);
    }

    private boolean canDeleteOrder(Order order) {
        return (null == order.getCustomer());
    }

}
