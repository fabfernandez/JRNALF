package com.mercadolibre.finalchallengedemo.dtos;


import java.time.LocalDate;

public class PartResponseDTO {

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
    private LocalDate lastModification;

    public PartResponseDTO(Integer partCode, String description, String maker, Integer quantity, Double discountType, Double normalPrice, Double urgentPrice, Integer netWeight, Integer longDimension, Integer widthDimension, Integer tallDimension, LocalDate lastModification) {
        this.partCode = partCode;
        this.description = description;
        this.maker = maker;
        this.quantity = quantity;
        this.discountType = discountType;
        this.normalPrice = normalPrice;
        this.urgentPrice = urgentPrice;
        this.netWeight = netWeight;
        this.longDimension = longDimension;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.lastModification = lastModification;
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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Double discountType) {
        this.discountType = discountType;
    }

    public Double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getUrgentPrice() {
        return urgentPrice;
    }

    public void setUrgentPrice(Double urgentPrice) {
        this.urgentPrice = urgentPrice;
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

    public LocalDate getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDate lastModification) {
        this.lastModification = lastModification;
    }
}
