package com.mercadolibre.finalchallengedemo.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter @Setter
public class PartDTO {

    private Integer partCode;
    private String description;
    //private String maker;
    private Integer quantity;
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
    @JsonIgnore
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastPriceModification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartDTO partDTO = (PartDTO) o;
        return partCode.equals(partDTO.partCode) && description.equals(partDTO.description) && normalPrice.equals(partDTO.normalPrice) && netWeight.equals(partDTO.netWeight) && longDimension.equals(partDTO.longDimension) && widthDimension.equals(partDTO.widthDimension) && tallDimension.equals(partDTO.tallDimension) && lastModification.equals(partDTO.lastModification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partCode, description, normalPrice, netWeight, longDimension, widthDimension, tallDimension, lastModification);
    }
}
