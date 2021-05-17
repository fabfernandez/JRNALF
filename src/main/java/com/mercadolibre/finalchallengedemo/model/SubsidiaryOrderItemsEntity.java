package com.mercadolibre.finalchallengedemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sub_order_items")
@Getter
@Setter
public class SubsidiaryOrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_sub_order_item")
    private Integer id;

    @Column(name="quantity")
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "order_id")
    private SubsidiaryOrderEntity subsidiaryOrder;

    @ManyToOne
    @JoinColumn(nullable = false, name = "part_id")
    private PartEntity part;

    @Column(nullable = false, name = "account_type")
    private String accountType;

    @Column(name="reason")
    private String reason;
}
