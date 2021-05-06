package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "parts_modifications")
public class PartModificationEntity {
    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private PartEntity part;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_part_modification")
    private Integer id_part_modification;

    private LocalDate last_modification;

    @Column(name = "normal_price")
    private Integer normalPrice;


}
