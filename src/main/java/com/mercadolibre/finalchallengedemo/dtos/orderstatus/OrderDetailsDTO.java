package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Validated
public class OrderDetailsDTO {

    private Integer orderNumber;

    private String description; // agregado

    private Date orderDate;

    private Date deliveryDate;

    private Integer daysDelay;

    private String deliveryStatus;

    private List<PartOrderDetailDTO> orderDetails;
}
