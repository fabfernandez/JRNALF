package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "subsidiary")
public class SubsidiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String adress;
    private Integer phone;
    private String country;

}
