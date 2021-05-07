package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Validated
public class OrderStatusResponseDTO {

    @Pattern(regexp = "^\\d{4}-\\d{8}$", message = "Order number does not match the required pattern.")
    private String orderNumberCE;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Size(min = 1, max = 1, message = "Order status code must be only one letter.")
    @Pattern(regexp = "^[PDFCpdfc]$", message = "Invalid order status code. ")
    private String orderStatus;

    private ArrayList<PartOrderDetailDTO> orderDetails;
}
