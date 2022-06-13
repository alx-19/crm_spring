package com.crm.crm_spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    /**
     * attributs
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(length = 30)
    private String lastname;

    @Column(length = 10)
    private Long phone;

    private String mail;

    private String password;

    @Column(length = 10)
    private String role;

    /**
     * relation
     */

// relation entre vendeur et acheteur
    @OneToMany(mappedBy = "dealer")
    private List<Customer> customer;

// relation entre vendeur et commande
    @OneToMany(mappedBy = "dealer")
    private List<Order> order;

}
