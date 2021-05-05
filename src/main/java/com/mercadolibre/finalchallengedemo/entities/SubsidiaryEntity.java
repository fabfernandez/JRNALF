package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subsidiary")
public class SubsidiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    @Size(max = 50, message = "The name of the subsidiary must have a miximium of 50 alphanumeric characters.")
    private String name;

    @Column(nullable = false, length = 50)
    @Size(max = 50, message = "The adress of the subsidiary must have a miximium of 50 alphanumeric characters.")
    private String adress;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "The phone of the subsidiary must have a miximium of 20 numeric characters.")
    private Integer phone;

    @Column(nullable = false, length = 10)
    @Size(max = 10, message = "The country of the subsidiary must have a miximium of 10 characters.")
    private String country;

}
