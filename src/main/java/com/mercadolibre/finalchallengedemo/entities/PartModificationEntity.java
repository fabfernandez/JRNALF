package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "parts_modifications")
public class PartModificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private PartEntity part;

    private LocalDate last_modification;

    @Column(name = "normal_price")
    private Integer normalPrice;
}
