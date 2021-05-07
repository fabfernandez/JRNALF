package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderDetailDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDetailsDTO {

    private Integer orderNumber;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer daysDelay;
    private String deliveryStatus;
    private List<PartOrderDetailDTO> orderDetails;
}
