package com.mercadolibre.finalchallengedemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "dealer_order_items")
@AllArgsConstructor
@NoArgsConstructor
// Entity that represents an item of a dealer's order, contains only a single part and the quantity ordered.
public class DealerOrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dealer_order_item", nullable = false)
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
