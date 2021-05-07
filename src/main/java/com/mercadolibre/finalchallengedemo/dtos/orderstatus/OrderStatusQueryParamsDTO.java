package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class OrderStatusQueryParamsDTO {

    @NotNull(message = "Order number cant be null or empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{8}$", message = "Order number does not match the required pattern.")
    private String orderNumberCM;
}
