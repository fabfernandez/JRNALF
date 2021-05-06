package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "subsidiaries")
public class SubsidiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 25)
    @Size(max = 25, message = "The name of the subsidiary must have a miximium of 25 alphanumeric characters.")
    private String name;

    @Column(nullable = false, length = 40)
    @Size(max = 40, message = "The address of the subsidiary must have a miximium of 40 alphanumeric characters.")
    private String address;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "The phone of the subsidiary must have a miximium of 20 numeric characters.")
    private Integer phone;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "The country of the subsidiary must have a miximium of 20 characters.")
    private String country;

    @OneToMany(mappedBy = "subsidiary")
    private Set<StockSubsidiaryEntity> subsidiaryEntities;

}
