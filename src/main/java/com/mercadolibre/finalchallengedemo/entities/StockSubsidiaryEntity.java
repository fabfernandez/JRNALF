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
@Table(name = "sub_stock")
@IdClass(RelationshipStockId.class)
public class StockSubsidiaryEntity implements Serializable {

    private Integer quantity;

    @Id
    @ManyToOne(cascade=CascadeType.ALL, optional=true, fetch=FetchType.EAGER)
    @JoinColumn(name = "part_code", nullable = false)
    private PartEntity part;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;
}
