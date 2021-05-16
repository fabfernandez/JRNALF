package com.mercadolibre.finalchallengedemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sub_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// UNA ORDEN DE UN DEALER ESPECIFICO
public class SubsidiaryOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_sub_order")
    private Integer orderNumber;

    @Column(nullable = false, name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(nullable = false, name = "order_status")
    private Character orderStatus;

    @Column(nullable = false, name = "subsidiary_id")
    private Integer subsidiaryId;


    @Column(nullable = false, name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(nullable = false, name = "days_delay")
    private Integer daysDelay;

    @OneToMany(mappedBy = "subsidiaryOrder", fetch = FetchType.LAZY, orphanRemoval = true )
    // ITEMS
    private Set<SubsidiaryOrderItemsEntity> orderDetails;

}
