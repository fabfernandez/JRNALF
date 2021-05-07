package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
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

}
