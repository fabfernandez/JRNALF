package com.mercadolibre.finalchallengedemo.dtos;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
@Data
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
