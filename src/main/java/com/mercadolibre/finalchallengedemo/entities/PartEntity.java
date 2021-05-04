package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_code")
    private Long id;

    private String description;
    private Integer widthDimension;
    private Integer tallDimension;
    private Integer longDimension;
    private Integer netWeight;

}
