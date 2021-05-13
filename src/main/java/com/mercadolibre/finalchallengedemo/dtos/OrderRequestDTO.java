package com.mercadolibre.finalchallengedemo.dtos;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderDetailDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.OverridesAttribute;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Data
@Validated
public class OrderRequestDTO {

    @NotNull(message = "The purchase requisition cannot be null or empty.")
    List<PartOrderDetailDTO> orderDetails;
    
}
