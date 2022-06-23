package com.crm.crm_spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Ajout des paramètres de Lombock pour générer toutes les méthodes, constructeurs, etc.
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    /**
     * attributs
     * revoir les noms des attributs, en ajouter, utiliser UK et ajouter des @columns + parameters
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(length = 30)
    private String lastname;

    @Column(length = 10)
    private String phone;

    private String mail;

    private String password;

    @Column(length = 10)
    private String grants;

    /**
     * relation
     */

// relation entre user et customer
    @OneToMany(mappedBy = "dealer")
    private List<Customer> customer;


    /*
        Revoir cette relation puisque l'id du customer
        est une FK de Order et donc on doit pouvoir récupérer
        l'id du user qui est une FK de customer
    */
// relation entre user et order
    @OneToMany(mappedBy = "dealer")
    private List<Order> order;

}
