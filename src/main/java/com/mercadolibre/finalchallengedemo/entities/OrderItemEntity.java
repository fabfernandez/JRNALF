package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
//esto es un item de una orden, que contiene UNA parte y que cantidad pide
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id_order_detail")
    private Integer id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private DealerOrderEntity correspondingOrder;

    @ManyToOne
    @JoinColumn(nullable = false, name = "part_id")
    private PartEntity part;

    @Column(nullable = false, name = "account_type")
    private String accountType;

    private String reason;

}
