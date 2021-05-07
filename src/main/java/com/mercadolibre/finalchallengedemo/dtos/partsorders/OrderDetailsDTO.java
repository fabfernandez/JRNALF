package com.mercadolibre.finalchallengedemo.dtos.partsorders;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDetailsDTO {

    private Integer orderNumberCM;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer daysDelayed;
    private String deliveryStatus;
    private List<PartOrderDetailDTO> ordersDetails;
}
