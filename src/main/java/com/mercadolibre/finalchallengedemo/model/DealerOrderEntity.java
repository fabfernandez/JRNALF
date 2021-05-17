package com.mercadolibre.finalchallengedemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "dealer_orders")
// Entity of an order from a specific dealer.
public class DealerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dealer_order", nullable = false)
    private Integer orderNumber;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "order_status", length = 1, nullable = false)
    @Size(max = 1, message = "The order status must only have an alphanumeric character.")
    private String orderStatus;

    @Column(name = "delivery_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "days_delay", nullable = false)
    private Integer daysDelay;

    @OneToMany(mappedBy = "correspondingOrder")
    private Set<DealerOrderItems> orderDetails;

    @Column(name = "dealer_id", nullable = false)
    private Integer dealerId;

    @Column(name = "subsidiary_id", nullable = false)
    private Integer subsidiaryId;
}
