package com.mercadolibre.finalchallengedemo.dtos;

import java.time.LocalDate;

public class PartDTO {

    private Integer partCode;
    private String description;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;

    public PartDTO(String description, Integer netWeight, Integer longDimension, Integer widthDimension, Integer tallDimension) {
        this.description = description;
        this.netWeight = netWeight;
        this.longDimension = longDimension;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
    }

    public Integer getPartCode() {
        return partCode;
    }

    public void setPartCode(Integer partCode) {
        this.partCode = partCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getLongDimension() {
        return longDimension;
    }

    public void setLongDimension(Integer longDimension) {
        this.longDimension = longDimension;
    }

    public Integer getWidthDimension() {
        return widthDimension;
    }

    public void setWidthDimension(Integer widthDimension) {
        this.widthDimension = widthDimension;
    }

    public Integer getTallDimension() {
        return tallDimension;
    }

    public void setTallDimension(Integer tallDimension) {
        this.tallDimension = tallDimension;
    }
}
