package com.mercadolibre.finalchallengedemo.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class PartDTO {

    @NotNull
    private Integer partCode;
    @Size(max = 100)
    private String description;
    @Size(max = 20)
    private String maker;
    @Min(0)
    private Integer widthDimension;
    @Min(0)
    private Integer tallDimension;
    @Min(0)
    private Integer longDimension;
    @Min(0)
    private Integer netWeight;
    @Min(0)
    private Double normalPrice;
    @Min(0)
    private Double urgentPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastModification;

    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastPriceModification;

    @JsonIgnore
    @Min(0)
    private Integer quantity;

}
