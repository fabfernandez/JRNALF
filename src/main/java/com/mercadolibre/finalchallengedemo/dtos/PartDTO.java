package com.mercadolibre.finalchallengedemo.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class PartDTO {

    private Integer partCode;
    private String description;
    //private String maker;
   // private Integer quantity;
   // private Double discountType;
    private Double normalPrice;
    //private Double urgentPrice;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastModification;

}
