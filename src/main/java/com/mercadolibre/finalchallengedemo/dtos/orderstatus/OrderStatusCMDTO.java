package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Data
@Validated
public class OrderStatusCMDTO {

    //This is the dto that handles the query parameter for REQ 3
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{8}$", message = "Order number does not match the required pattern.")
    private String orderNumberCM;
}
