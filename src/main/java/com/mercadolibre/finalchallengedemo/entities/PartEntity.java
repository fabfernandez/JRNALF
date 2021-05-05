package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "parts")
@Getter
public class PartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_code")
    private Long partCode;
    private String description;
    private Integer widthDimension;
    private Integer tallDimension;
    private Integer longDimension;
    private Integer netWeight;
}
