package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id_order_detail")
    private Integer idOrderDetail;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private DealerOrderEntity dealerOrderEntityID;

    @ManyToOne
    @JoinColumn(nullable = false, name = "part_id")
    private PartEntity partEntityId;

}
