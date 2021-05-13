package com.mercadolibre.finalchallengedemo.dtos;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderItemDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class OrderRequestDTO {

    @NotNull(message = "The purchase requisition cannot be null or empty.")
    List<OrderItemDTO> orderDetails;
    
}
