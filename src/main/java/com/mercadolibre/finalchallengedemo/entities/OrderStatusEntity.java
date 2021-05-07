package com.mercadolibre.finalchallengedemo.entities;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderDetailsDTO;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "orderStatus")
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "order_id")
    private Integer orderId;

    @Column(nullable = false, name = "order_number_ce")
    private String orderNumberCE;

    @Column(nullable = false, name = "order_date")
    private String orderDate;

    @Column(nullable = false, name = "order_status")
    private Integer orderStatus;


    //TODO: Komo C Ase esto? Juan de las 3 am pregunta
    //@Column(nullable = false, name = "order_details")
    //private ArrayList<> orderDetails;
}
