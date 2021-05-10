package com.mercadolibre.finalchallengedemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class RelationshipStockId implements Serializable {

    private PartEntity part;
    private SubsidiaryEntity subsidiary;
}
