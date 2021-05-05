package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dealer_orders")
public class DealerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "order_number")
    private Integer order_number;

    @Column(nullable = false, name = "order_date")
    private LocalDate orderDate;

    @Column(nullable = false, name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(nullable = false, name = "days_delay")
    private Integer daysDelay;

    @Column(nullable = false, name = "delivery_status", length = 1)
    @Size(max = 1, message = "The status must not have more than 1 character.")
    private String deliveryStatus;

    @Column(nullable = false, name = "dealer_id")
    private Integer dealerId;

    @Column(nullable = false, name = "subsidiary_id")
    private Integer subsidiaryId;
}
