package com.mercadolibre.finalchallengedemo.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class OrderUpdateRequestDTO {

    @NotNull(message = "Order number can't be null or empty")
    private Integer orderNumber;

    @NotNull(message = "Status code can't be null or empty")
    @Pattern(regexp = "^[CFDPcfdp]$", message = "Invalid delivery status code.")
    private String statusCode;
}
