package com.mercadolibre.finalchallengedemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartDTO {
    private Long partCode;
    private String description;
    private Integer widthDimension;
    private Integer tallDimension;
    private Integer longDimension;
    private Integer netWeight;

}
