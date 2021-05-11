package com.mercadolibre.finalchallengedemo.entities;

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
// Entity of an order from a specific leader.
public class DealerOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dealer_order", nullable = false)
    private Integer orderNumber;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "order_status", length = 1, nullable = false)
    @Size(max = 1, message = "The order status must only have an alphanumeric character.")
    private String orderStatus;

    @Column(name = "delivery_date", nullable = false)
    private Date deliveryDate;

    @Column(name = "days_delay", nullable = false)
    private Integer daysDelay;

    @OneToMany(mappedBy = "correspondingOrder")
    // ITEMS
    private Set<OrderItemEntity> orderDetails;

    @Column(name = "dealer_id", nullable = false)
    private Integer dealerId;
    // Create a column called dealer_id made up of a FK referenced to dealers
    // Crea una columna llamada dealer_id compuesta de una FK referenciada a dealers
    /*@ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private DealerEntity dealerId;
    */

    @Column(name = "subsidiary_id", nullable = false)
    private Integer subsidiaryId;
    // Create a column called subsidiary_id made up of a FK referenced to subsidiaries
    // Crea una columna llamada subsidiary_id compuesta de una FK referenciada a subsidiaries
    /*@ManyToOne
    @JoinColumn(name = "subsidiary_id", nullable = false)
    private SubsidiaryEntity subsidiaryId;
    */
}
