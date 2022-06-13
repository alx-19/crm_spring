package com.crm.crm_spring.service;

import com.crm.crm_spring.model.Order;

import java.util.List;

public interface OrderService {

	/**
	 * Get all orders
	 * @return all orders
	 */
	List<Order> getAll();

	/**
	 * Get order by id
	 * @param id the id
	 * @return id the id
	 */
	Order getById(Integer id);

	/**
	 * Create order
	 * @param order the order to create
	 * @return the created order
	 */
	Order create(Order order);

	/**
	 * Delete order
	 * @param id the order id
	 */
	void delete(Integer id);

	/**
	 * Update order
	 * @param order the order
	 * @return the updated order
	 */
	Order update(Order order);

}
