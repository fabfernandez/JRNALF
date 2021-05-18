package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Validated
//REQ 3
// es un item de una orden, pero con partStatus.
public class OrderItemPartStatusDTO {
    private Integer partCode;

    @Size(max = 100, message = "The description of the spare part must have a maximium of 100 alphanumeric characters.")
    private String description;

    @Min(value = 0, message = "Quantity must be a positive integer")
    private Integer quantity;

    @Pattern(regexp = "^[RGrg]$", message = "Invalid order status code. ")
    private String accountType; //Warranty or Spare Parts account type G or R

    @Size(max = 100, message = "The reason for which the spare part is pending must have a maximium of 100 alphanumeric characters.")
    private String reason;

    @Size(min = 1, max = 1, message = "This field must be only 1 character in length")
    private String partStatus;
}
