package com.mercadolibre.finalchallengedemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subsidiaries")
public class SubsidiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subsidiary")
    private Integer id;

    @Column(name = "sub_name", nullable = false, length = 25)
    @Size(max = 25, message = "The name of the subsidiary must have a maximium of 25 alphanumeric characters.")
    private String name;

    @Column(name = "sub_address", nullable = false, length = 40)
    @Size(max = 40, message = "The address of the subsidiary must have a maximium of 40 alphanumeric characters.")
    private String address;

    @Column(name = "sub_phone", nullable = false, length = 20)
    //@Size(max = 20, message = "The phone of the subsidiary must have a maximium of 20 numeric characters.")
    private Integer phone;

    @Column(name = "sub_country", nullable = false, length = 20)
    @Size(max = 20, message = "The country of the subsidiary must have a maximium of 20 characters.")
    private String country;

    @OneToMany(mappedBy = "subsidiary")
    private Set<StockSubsidiaryEntity> stockSubsidiaryEntities;
}
