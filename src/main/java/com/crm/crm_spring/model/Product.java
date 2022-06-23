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
     * revoir les noms des attributs, en ajouter, utiliser UK et ajouter des @columns  + parameters
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

    /* Attentio a cette relation car il ne doit pas y avoir de FK order_id dans product */
// relation entre une commande et un produit
    @OneToMany(mappedBy = "house")
    private List <Order> order;

}
