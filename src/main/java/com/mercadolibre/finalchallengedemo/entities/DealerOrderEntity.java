package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "dealer_orders")
@Getter
@Setter
// UNA ORDEN DE UN DEALER ESPECIFICO
public class DealerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "order_number")
    private Integer orderNumber;

    @Column(nullable = false, name = "order_date")
    private Date orderDate;

    @Column(nullable = false, name = "order_status")
    private String orderStatus;

    @Column(nullable = false, name = "dealer_id")
    private Integer dealerId;

    @Column(nullable = false, name = "subsidiary_id")
    private Integer subsidiaryId;


    @Column(nullable = false, name = "delivery_date")
    private Date deliveryDate;

    @Column(nullable = false, name = "days_delay")
    private Integer daysDelay;

    @OneToMany(mappedBy = "correspondingOrder")
    // ITEMS
    private Set<OrderItemEntity> orderDetails;

}
