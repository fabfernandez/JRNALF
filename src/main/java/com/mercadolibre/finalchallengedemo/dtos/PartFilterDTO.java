package com.mercadolibre.finalchallengedemo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class PartFilterDTO {

    private Character queryType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Integer order;

    public PartFilterDTO(Character queryType, LocalDate date, Integer order) {
        this.queryType = queryType;
        this.date = date;
        this.order = order;
    }

    public Character getQueryType() {
        return queryType;
    }

    public void setQueryType(Character queryType) {
        this.queryType = queryType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean hasFilters() {
        return (getDate() != null && getOrder() != null && getQueryType() != null);
    }

}
