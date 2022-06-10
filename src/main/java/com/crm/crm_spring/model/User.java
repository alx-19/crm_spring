package com.crm.crm_spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

    /**
     * attributs
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String firstname;

    @Column(length = 30)
    private String lastname;

    @Column(length = 10)
    private Long phone;

    @Column(length = 255)
    private String mail;

    @Column(length = 255)
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
