package com.crm.crm_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * attributs
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String lastname;

    @Column(length = 100)
    private String firstname;

    @Column(length = 5)
    private Integer streetNumber;

    @Column(length = 30)
    private String streetName;

    @Column(length = 10)
    private Integer zipcode;

    @Column(length = 30)
    private String city;

    @Column(length = 30)
    private String mail;

    @Column(length = 15)
    private String phone;

    @Column(length = 15)
    private String mobile;

    private String notes;

    private Boolean active;

    /**
     * relations
     */

// relation entre vendeur et acheteur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User dealer;

// relation entre un acheteur et un produits
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

}
