package com.crm.crm_spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    /**
     * attributs
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String type;

    @Column(length = 6)
    private Long priceHt;

    @Column(length = 5)
    private Long surface;

    private String photoUrl;

    private String description;


    /**
     * relation
     */

// relation entre une commande et un produit
    @OneToMany(mappedBy = "house")
    private List <Order> order;

}
