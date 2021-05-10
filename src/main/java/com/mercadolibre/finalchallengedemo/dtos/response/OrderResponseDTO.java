package com.mercadolibre.finalchallengedemo.dtos.response;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDTO {
    private String orderNumberCE;
    private Date orderDate;
    private String orderStatus;
    private List<PartOrderDetailDTO> orderDetails;
}
