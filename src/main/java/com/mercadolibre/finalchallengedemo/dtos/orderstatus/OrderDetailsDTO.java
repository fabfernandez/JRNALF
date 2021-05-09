package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailsDTO {

    private Integer orderNumber;
    private Date orderDate;
    private Date deliveryDate;
    private Integer daysDelay;
    private String deliveryStatus;
    private List<PartOrderDetailDTO> orderDetails;
}
