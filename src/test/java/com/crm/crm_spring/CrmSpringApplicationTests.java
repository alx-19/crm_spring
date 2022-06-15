package com.crm.crm_spring;

import com.crm.crm_spring.model.Customer;
import com.crm.crm_spring.model.Order;
import com.crm.crm_spring.model.Product;
import com.crm.crm_spring.repository.CustomerRepository;
import com.crm.crm_spring.repository.OrderRepository;
import com.crm.crm_spring.repository.ProductRepository;
import com.crm.crm_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class CrmSpringApplicationTests {

    // declaration d'un variable produit de type repository pour pouvoir faire tous les test sur les produits
    private final ProductRepository productRepository;
    // declaration d'un variable produit de type repository pour pouvoir faire tous les test sur les commandes
    private final OrderRepository orderRepository;
    // declaration d'un variable produit de type repository pour pouvoir faire tous les test sur les clients
    private final CustomerRepository customerRepository;
    // declaration d'un variable produit de type repository pour pouvoir faire tous les test sur les utilisateurs
    private final UserRepository userRepository;

    /**
     *  Tests unitaires pour classe Product
     */

    @Test
    public void testCreateProduct() {
        // creation d'un produit avec propriété + grace a produit repository et ses methodes qu'il extend je le fige en bdd
        Product prod = new Product();
        productRepository.save(prod);
    }

    @Test
    public void testFindProduct() {
        // test du find produit avec .get() sinon pour obeservable
        Product p = productRepository.findById(3).get();
        System.out.println(p);
    }

    @Test
    public void testUpdateProduct() {
        Product p = productRepository.findById(3).get();
        p.setName("testUnitaire10");
        productRepository.save(p);
    }

    // ne pas supprimer admin et user ID 1 & 2
    @Test
    public void testDeleteProduct() {
        productRepository.deleteById(3);
    }

    @Test
    public void testFindAllProducts() {
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
        // creation d'un produit avec propriété + grace a produit repository et ses methodes qu'il extend je le fige en bdd
        Order prod = new Order();
        orderRepository.save(prod);
    }

    @Test
    public void testFindOrder() {
        // test du find produit avec .get() sinon pour obeservable
        Order o = orderRepository.findById(3).get();
        System.out.println(o);
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
        // creation d'un produit avec propriété + grace a produit repository et ses methodes qu'il extend je le fige en bdd
        Customer prod = new Customer();
        customerRepository.save(prod);
    }

    @Test
    public void testFindCustomer() {
        // test du find produit avec .get() sinon pour obeservable
        Customer c = customerRepository.findById(3).get();
        System.out.println(c);
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
}
