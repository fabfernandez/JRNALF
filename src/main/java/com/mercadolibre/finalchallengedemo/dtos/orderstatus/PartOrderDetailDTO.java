package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Validated
//req 2
//respuesta, dentro de cada "orderDetails" del "orders"
public class PartOrderDetailDTO {

    private Integer partCode;
    private String description;

    @Min(value = 0, message = "Quantity must be a positive integer")
    private Integer quantity;

    @Pattern(regexp = "^[RGrg]$", message = "Invalid order status code. ")
    private String accountType; //Warranty or Spare Parts account type G or R

    @Size(max = 100)
    private String reason;     //Why is the order pending

}
