package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "dealer_orders")
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
}
