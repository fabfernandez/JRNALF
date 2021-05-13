package com.mercadolibre.finalchallengedemo.dtos.response;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
// Deberia ser el order response del req 3

// Este en teoria deberia volar!!!!!!!!
public class OrderResponseDTO {

    // Chequear response
    @Size(min = 12, max = 12, message = "12 numeric characters are required.")
    @Pattern(regexp = "^\\d{4}-\\d{8}$", message = "Order number does not match the required pattern.")
    private String orderNumber; // lo reemplace por orderNumber

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate; // LocalDate???????????

    @Size(min = 1, max = 1, message = "Order status code must be only 1 character.")
    @Pattern(regexp = "^[PDFCpdfc]$", message = "Invalid delivery status code.")
    private String orderStatus;

    private List<OrderItemDTO> orderDetails;
}
