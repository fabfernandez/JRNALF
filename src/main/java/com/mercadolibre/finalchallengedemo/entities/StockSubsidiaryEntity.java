package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "subsidiaries_stock")
public class StockSubsidiaryEntity {

    @Id
    //TODO Hacer andar id autoincremental
    private Integer stockId;

    private Integer quantity;


    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "part_code")
    private PartEntity part;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subsidiary")
    private SubsidiaryEntity subsidiary;
}
