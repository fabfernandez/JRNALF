package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_details")
// Entity with an item of an order, contains only a part and the quantity that is required.
//esto es un item de una orden, que contiene UNA parte y que cantidad pide
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id_order_detail")
    private Integer id;

    private Integer quantity;

    private String reason;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private DealerOrderEntity correspondingOrder;

    @ManyToOne
    @JoinColumn(name = "part_id" ,nullable = false)
    private PartEntity part;
}
