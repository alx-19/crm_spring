package com.crm.crm_spring.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    /**
     * attributs
     * revoir les noms des attributs, en ajouter, utiliser UK et ajouter des @columns  + parameters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private Integer reference;

    @Column(length = 10)
    private String status;

    @Column(length = 10)
    private String date;

    /**
     * relations
     */

// relation entre user et order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User dealer;

// relation entre une order et un product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product house;

// relation entre un customer et un product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
