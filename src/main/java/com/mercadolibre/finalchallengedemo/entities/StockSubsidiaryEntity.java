package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "stock_subsidiary")
public class StockSubsidiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private PartEntity parts;

    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;
}
