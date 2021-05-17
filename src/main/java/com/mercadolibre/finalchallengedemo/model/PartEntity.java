package com.mercadolibre.finalchallengedemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "parts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartEntity {

    @Id
    @Column(name = "id_part")
    //@Size(min = 8, message = "The field needs a minimum of 8 numeric characters")
    private Integer partCode;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "The field needs a maximium of 100 alphanumeric characters.")
    private String description;

    @Column(name ="maker")
    private String maker;

    @Column(name ="discount_type")
    private String discountType;

    @Column(name="width_dimension", nullable = false, length = 4)
    //@Size(max = 4, message = "The width of the spare part needs 4 numeric characters.")
    private Integer widthDimension;

    @Column(name="tall_dimension", nullable = false, length = 4)
    //@Size(max = 4, message = "The height of the spare part needs 4 numeric characters.")
    private Integer tallDimension;

    @Column(name="long_dimension", nullable = false, length = 4)
    //@Size(max = 4, message = "The length of the spare part needs 4 numeric characters.")
    private Integer longDimension;

    @Column(name="net_weight", nullable = false, length = 5)
    //@Size(max = 5, message = "The net weight of the spare part needs 5 numeric characters.")
    private Integer netWeight;

    @Column(name = "normal_price")
    private Integer normalPrice;

    @Column(name = "urgent_price")
    private Integer urgentPrice;

    @Column(name ="last_modification")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastModification;

    @Column(name ="last_price_modification")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastPriceModification;

    @Column(name = "part_status")
    private String partStatus;

    @OneToMany(mappedBy = "part")
    private Set<StockSubsidiaryEntity> stockSubsidiaryEntities;

}