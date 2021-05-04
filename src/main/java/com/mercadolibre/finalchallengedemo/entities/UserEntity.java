package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;

    private String password;

    private String role;

    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;

}
