package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "orderNumberCM")
    private Long id;

}
