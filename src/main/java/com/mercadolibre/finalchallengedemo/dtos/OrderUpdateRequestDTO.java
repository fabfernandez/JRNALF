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
@Data
@Validated
public class OrderUpdateRequestDTO {

    @NotNull(message = "Order number can't be null or empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{8}$", message = "Order number doesn't match the required pattern.")
    private Integer orderNumber;

    @Size(min = 1, max = 1, message = "Status code must be only 1 character.")
    @Pattern(regexp = "^[CFDPcfdp]$", message = "Invalid delivery status code.")
    private Character statusCode;
}
