package com.mercadolibre.finalchallengedemo.dtos;

import com.mercadolibre.finalchallengedemo.dtos.OrdersDetailsDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class OrderResponseDTO {

    private Integer orderNumberCM;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer daysDelayed;
    private String deliveryStatus;
    private List<OrdersDetailsDTO> ordersDetails;
}
