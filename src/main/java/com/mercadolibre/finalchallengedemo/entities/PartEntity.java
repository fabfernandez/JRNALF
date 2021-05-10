package com.mercadolibre.finalchallengedemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "parts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "part_code")
    @Size(min = 8, message = "The field needs a minimum of 8 numeric characters")
    private Integer partCode;

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

    @Column(name = "normal_price")
    private Integer normalPrice;

    @Column(name = "urgent_price")
    private Integer urgentPrice;

    @Column(name ="last_modification")
    private Date lastModification;

    @Column(name ="last_price_modification")
    private Date lastPriceModification;

    @Column(name ="maker")
    private String maker;

    @Column(name ="discount_type")
    private String discountType;

    @OneToMany(mappedBy = "part")
    private Set<StockSubsidiaryEntity> stockSubsidiaryEntities;




}
