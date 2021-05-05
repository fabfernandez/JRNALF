package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "parts_modifications")
public class PartModificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate last_modification;

    @Column(name = "normal_price")
    private Integer normalPrice;

    @ManyToOne
    @JoinColumn(name = "part_code", nullable = false)
    private PartEntity part;

}
