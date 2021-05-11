package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
@AllArgsConstructor
public class OrderStatusQueryParamsDTO {

    @NotNull(message = "Order number can't be null or empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{8}$", message = "Order number doesn't match the required pattern.")
    private String orderNumberCM;
}
