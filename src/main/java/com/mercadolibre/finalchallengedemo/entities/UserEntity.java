package com.mercadolibre.finalchallengedemo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    @Size(max = 20, message = "The username needs a minimium of 20 alphanumeric characters.")
    private String username;

    @Column(nullable = false, length = 20)
    @Size(min = 8, message = "The password needs a minimium of 8 alphanumeric characters.")
    private String password;

    @Column(nullable = false, length = 15)
    @NotNull(message = "The user roll must not be null.")
    @Size(max = 15, message = "The password needs a maximium of 15 alphanumeric characters.")
    private String role;

    @ManyToOne
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private SubsidiaryEntity subsidiary;

}
