package com.mercadolibre.finalchallengedemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "dealers")
public class DealerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, length = 1, name = "id_dealer")
    private Integer idDealer;

    @Column(nullable = false, length = 25)
    @Size(max = 25, message = "The country must not have more than 20 characters")
    private String name;

    @Column(nullable = false, length = 40)
    @Size(max = 40, message = "The adress must not have more than 20 characters")
    private String address;

    @Column(nullable = false)
    private Integer phone;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "The country must not have more than 20 characters")
    private String country;

    // Create the relationship with dealer_orders through the field called dealerId
    // crea la realacion con dealers a traves del campo llamado dealerId
    /*
    @OneToMany(mappedBy = "dealerId", fetch = FetchType.LAZY)
    private DealerOrderEntity dealerOder;
    */


}
