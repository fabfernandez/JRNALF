package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dealer_orders")
@Getter
@Setter
public class DealerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "order_number")
    private Integer orderNumber;

    @Column(nullable = false, name = "order_date")
    private LocalDate orderDate;

    @Column(nullable = false, name = "order_status")
    private String orderStatus;

    @Column(nullable = false, name = "dealer_id")
    private Integer dealerId;

    @Column(nullable = false, name = "subsidiary_id")
    private Integer subsidiaryId;

    /*
    @Column(nullable = false, name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(nullable = false, name = "days_delay")
    private Integer daysDelay;

    @Column(nullable = false, name = "delivery_status", length = 1)
    @Size(max = 1, message = "The status must not have more than 1 character.")
    private String deliveryStatus;
     */
}
