package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "subsidiaries_stock")
public class StockSubsidiaryEntity {

    // Tengo dudas con el id, es necesario????
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private PartEntity parts;

    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;
}
