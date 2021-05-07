package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subsidiaries_stock")
public class StockSubsidiaryEntity implements Serializable {


    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "part_code", nullable = false)
    private PartEntity part;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;
}
