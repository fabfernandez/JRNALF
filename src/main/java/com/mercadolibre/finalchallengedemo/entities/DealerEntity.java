package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dealer")
public class DealerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1)
    @NotNull(message = "Status cannot be null")
    private Character status;

}
