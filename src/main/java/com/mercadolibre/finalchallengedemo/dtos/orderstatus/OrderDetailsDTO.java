package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class OrderDetailsDTO {

    private Integer orderNumber;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    private Integer daysDelay;

    private String deliveryStatus;

    private List<OrderItemDTO> orderDetails;
}
