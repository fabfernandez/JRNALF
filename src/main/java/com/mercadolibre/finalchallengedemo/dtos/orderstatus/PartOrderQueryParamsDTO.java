package com.mercadolibre.finalchallengedemo.dtos.orderstatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class PartOrderQueryParamsDTO {

    @NotNull(message = "Must provide a dealer number")
    private String dealerNumber;

    @Size(min = 1, max = 1, message = "Delivery status code must be only 1 character.")
    @Pattern(regexp = "^[PDFCpdfc]$", message = "Invalid delivery status code.")
    private String deliveryStatus;

    @Digits(integer = 1, fraction = 0, message = "Invalid order type")
    @DecimalMin(value = "1", message = "The order value must be 1 or 2")
    @DecimalMax(value = "2", message = "The order value must be 1 or 2")
    private Integer order;

}
