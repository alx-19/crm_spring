package com.crm.crm_spring;

import com.crm.crm_spring.exception.UnknownResourceException;
import com.crm.crm_spring.model.Customer;
import com.crm.crm_spring.model.Order;
import com.crm.crm_spring.model.Product;
import com.crm.crm_spring.model.User;
import com.crm.crm_spring.repository.CustomerRepository;
import com.crm.crm_spring.repository.OrderRepository;
import com.crm.crm_spring.repository.ProductRepository;
import com.crm.crm_spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmSpringApplicationTests {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     *  Tests unitaires pour classe Product
     */
    @Test
    public void testCreateProduct() {
        // creation d'un produit avec propriété
        Product prod = new Product();
        productRepository.save(prod);
    }

    @Test
    public void testFindProduct() {
        // test du find
        Product pInBase = productRepository.findById(3)
                .orElseThrow(UnknownResourceException::new);
    }

    @Test
    public void testUpdateProduct() {
        // test update
        Product p = productRepository.findById(3).get();
        p.setName("testUnitaire10");
        productRepository.save(p);
        System.out.println(p.getName());
    }

    @Test
    public void testDeleteProduct() {
        // test du delete
        productRepository.deleteById(3);
    }

    @Test
    public void testFindAllProducts() {
        // test de récupeartion général
        List<Product> prods = productRepository.findAll();
        for (Product p : prods) {
            System.out.println(p);
        }
    }

    /**
     *  Tests unitaires pour classe Order
     */

    @Test
    public void testCreateOrder() {
        // creation d'une commande avec propriété
        Order prod = new Order();
        orderRepository.save(prod);
    }

    @Test
    public void testFindOrder() {
        // test du find
        Order oInBase = orderRepository.findById(3)
                .orElseThrow(UnknownResourceException::new);
    }

    @Test
    public void testUpdateOrder() {
        Order o = orderRepository.findById(3).get();
        o.setStatus("En cours de test unitaire");
        orderRepository.save(o);
    }

    @Test
    public void testDeleteOrder() {
        orderRepository.deleteById(3);
    }

    @Test
    public void testFindAllOrders() {
        List<Order> prods = orderRepository.findAll();
        for (Order o : prods) {
            System.out.println(o);
        }
    }

    /**
     *  Tests unitaires pour classe Customer
     */
    @Test
    public void testCreateCustomer() {
        Customer prod = new Customer();
        customerRepository.save(prod);
    }

    @Test
    public void testFindCustomer() {
        Customer cInBase = customerRepository.findById(3)
                .orElseThrow(UnknownResourceException::new);
    }

    @Test
    public void testUpdateCustomer() {
        Customer c = customerRepository.findById(3).get();
        c.setFirstname("test unitaire firstname");
        customerRepository.save(c);
    }

    @Test
    public void testDeleteCustomer() {
        customerRepository.deleteById(3);
    }

    @Test
    public void testFindAllCustomers() {
        List<Customer> prods = customerRepository.findAll();
        for (Customer c : prods) {
            System.out.println(c);
        }
    }

    /**
     *  Tests unitaires pour classe user
     */

    @Test
    public void testCreateUser() {
        User prod = new User();
        userRepository.save(prod);
    }

    @Test
    public void testFindUser() {
        User uInBase = userRepository.findById(3)
                .orElseThrow(UnknownResourceException::new);
    }

    @Test
    public void testUpdateUser() {
        User u = userRepository.findById(3).get();
        u.setUsername("test");
        userRepository.save(u);
    }

    @Test
    public void testDeleteUser() {
        userRepository.deleteById(3);
    }

    @Test
    public void testFindAllUsers() {
        List<User> prods = userRepository.findAll();
        for (User p : prods) {
            System.out.println(p);
        }
    }
}
