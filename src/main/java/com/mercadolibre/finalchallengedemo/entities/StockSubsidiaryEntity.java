package com.mercadolibre.finalchallengedemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_stock")
@IdClass(RelationshipStockId.class)
public class StockSubsidiaryEntity implements Serializable{

    private Integer quantity;

    @Id
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "part_code")
    private PartEntity part;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subsidiary")
    private SubsidiaryEntity subsidiary;


}
