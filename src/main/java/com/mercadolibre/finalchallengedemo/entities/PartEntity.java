package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "parts")
@Getter
public class PartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_code")
    @Size(min = 8, message = "The field needs a minimum of 8 numeric characters")
    private Long partCode;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "The field needs a miximium of 100 alphanumeric characters.")
    private String description;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The width of the spare part needs 4 numeric characters.")
    private Integer widthDimension;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The height of the spare part needs 4 numeric characters.")
    private Integer tallDimension;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The length of the spare part needs 4 numeric characters.")
    private Integer longDimension;

    @Column(nullable = false, length = 5)
    @Size(max = 5, message = "The net weight of the spare part needs 5 numeric characters.")
    private Integer netWeight;
}
