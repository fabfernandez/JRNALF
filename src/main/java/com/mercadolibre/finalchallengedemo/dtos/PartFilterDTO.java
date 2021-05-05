package com.mercadolibre.finalchallengedemo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Data
public class PartFilterDTO {
    //@Pattern(regexp = "CPV",message = "The queryType must be C, P or V")
    private Character queryType;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "The date must be past or equals to current date")
    private LocalDate date;
    @Digits(integer=1, fraction = 0, message = "The order value must be 1,2 or 3")
    @DecimalMin(value = "1", message = "The order value must be 1,2 or 3")
    @DecimalMax(value = "3", message = "The order value must be 1,2 or 3")
    private Integer order;

    public boolean hasFilters() {
        return (getDate() != null || getOrder() != null || getQueryType() != null);
    }

}
