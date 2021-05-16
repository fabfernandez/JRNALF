package com.mercadolibre.finalchallengedemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class  UserEntity {

    @Id
    private String username;

    private String password;

    // Create a column called id_subsiriary with FK referenced to the subsidiaries table.
    // Crea una columna llamada id_subsiriay con FK referenciada a la tabla subsidiaries.
    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;

}