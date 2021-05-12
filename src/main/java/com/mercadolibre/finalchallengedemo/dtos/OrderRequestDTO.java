package com.mercadolibre.finalchallengedemo.dtos;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderDetailDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.OverridesAttribute;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequestDTO {
    List<PartOrderDetailDTO> orderDetails;
}
