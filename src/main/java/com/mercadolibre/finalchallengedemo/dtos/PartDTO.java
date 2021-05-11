package com.mercadolibre.finalchallengedemo.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartDTO {
    public PartDTO(Integer partCode, String description, String maker, Integer quantity, String discountType, Integer widthDimension, Integer tallDimension, Integer longDimension, Integer netWeight, Double normalPrice, Double urgentPrice, String lastModification) {
        this.partCode = partCode;
        this.description = description;
        this.maker = maker;
        this.quantity = quantity;
        this.discountType = discountType;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.longDimension = longDimension;
        this.netWeight = netWeight;
        this.normalPrice = normalPrice;
        this.urgentPrice = urgentPrice;
        this.lastModification = lastModification;
    }

    @NotNull
    private Integer partCode;
    @Size(max = 100)
    private String description;
    @Size(max = 20)
    private String maker;
    @Min(0)
    private Integer quantity;
    //todo validate
    private String discountType;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String lastModification;

    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastPriceModification;

}
