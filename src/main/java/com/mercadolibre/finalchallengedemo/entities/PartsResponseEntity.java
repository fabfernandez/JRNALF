package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "parts")
@SecondaryTable(name = "part_modification", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_part_modification"))
public class PartsResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_code")
    @Size(min = 8, message = "The field needs a minimum of 8 numeric characters")
    private Integer id;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "The field needs a miximium of 100 alphanumeric characters.")
    private String description;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The width of the spare part needs 4 numeric characters.")
    private Integer widthDimension;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The height of the spare part needs 4 numeric characters.")
    private Integer tallDimension;

    @Column(nullable = false, length = 4)
    @Size(max = 4, message = "The length of the spare part needs 4 numeric characters.")
    private Integer longDimension;

    @Column(nullable = false, length = 5)
    @Size(max = 5, message = "The net weight of the spare part needs 5 numeric characters.")
    private Integer netWeight;

    @OneToMany(mappedBy = "parts")
    private Set<PartModificationEntity> partModificationEntity;

    @Column(name = "normal_price", table = "parts_modifications")
    private Integer normalPrice;
    
    @Column(name = "last_modification", table = "parts_modifications")
    private LocalDate lastModification;
}
