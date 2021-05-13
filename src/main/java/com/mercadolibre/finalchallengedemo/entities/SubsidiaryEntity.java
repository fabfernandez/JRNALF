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

    @Column(nullable = false, length = 25, name = "sub_name")
    @Size(max = 25, message = "The name of the subsidiary must have a maximium of 25 alphanumeric characters.")
    private String name;

    @Column(nullable = false, length = 40, name = "sub_address")
    @Size(max = 40, message = "The address of the subsidiary must have a maximium of 40 alphanumeric characters.")
    private String address;

    @Column(nullable = false, length = 20, name = "sub_phone")
    //@Size(max = 20, message = "The phone of the subsidiary must have a maximium of 20 numeric characters.")
    private Integer phone;

    @Column(nullable = false, length = 20, name = "sub_country")
    @Size(max = 20, message = "The country of the subsidiary must have a maximium of 20 characters.")
    private String country;

    //
    @OneToMany(mappedBy = "subsidiary")
    private Set<StockSubsidiaryEntity> stockSubsidiaryEntities;

    //@ManyToMany(mappedBy = "part_code")
    //private Set<PartEntity> parts;

    /*
    // Create the relationship with user throgh the username(PK of users) column
    // crea una relacion con user a traves de la columna de username
    @OneToMany(mappedBy = "subsidiary", fetch = FetchType.LAZY)
    private UserEntity users;
    */
}
