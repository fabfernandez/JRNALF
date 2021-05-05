package com.mercadolibre.finalchallengedemo.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PartsDTO {

    private Integer partCode;
    private String description;
    private String maker;
    private Integer quantity;
    private Double discountType;
    private Double normalPrice;
    private Double urgentPrice;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate lastModification;

}
