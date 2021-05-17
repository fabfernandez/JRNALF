package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import com.mercadolibre.finalchallengedemo.model.DealerOrderItems;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class DealerOrdersDTO {

    private Integer orderNumber;
    private Date orderDate;
    private String orderStatus;
    private Date deliveryDate;
    private Integer daysDelay;
    private Set<OrderItemDTO> orderDetails;
    private Integer dealerId;
    private Integer subsidiaryId;


}
