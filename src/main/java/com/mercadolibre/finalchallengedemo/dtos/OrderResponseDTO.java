package com.mercadolibre.finalchallengedemo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class OrderResponseDTO {

    private Integer orderNumberCM;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer daysDelayed;
    private String deliveryStatus;
}
