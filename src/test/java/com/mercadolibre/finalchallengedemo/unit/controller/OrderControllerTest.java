package com.mercadolibre.finalchallengedemo.unit.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.controller.OrderController;
import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.OrderUpdateRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderDetailsDTO;
import com.mercadolibre.finalchallengedemo.exceptions.ForbiddenAccessException;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderControllerTest {
    @Mock
    private static OrderServiceImpl orderService;
    private static OrderController orderController;

    @BeforeEach
    public void beforeEach() {
        openMocks(this);
        orderController = new OrderController(orderService);
    }

    @BeforeAll
    public static void beforeAll() {
    }

    @Test
    @DisplayName("When create order, then return ok response")
    void whenCreateOrder_thenReturnOkResponse() {
        OrderDetailsDTO orderDetailsDTO = getObject("classpath:subsidiaryOrder.json",OrderDetailsDTO.class);
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderDetails(orderDetailsDTO.getOrderDetails());
        when(orderService.createOrder(any())).thenReturn(orderDetailsDTO);
        assertEquals(HttpStatus.OK, orderController.createOrder(orderRequestDTO).getStatusCode());
    }

    @Test
    @DisplayName("When create order, then return ok response")
    void whenCreateOrderWithNonCentralHouseUser_thenThrowException() {
        DecodeToken.location = 1;
        assertThrows(ForbiddenAccessException.class, () -> orderController.createOrder(new OrderRequestDTO()).getStatusCode());
    }

    @Test
    @DisplayName("When create order, then return ok response")
    void whenUpdateOrder_thenReturnOkResponse() {
        DecodeToken.location = 1;
        OrderUpdateRequestDTO updateRequest = new OrderUpdateRequestDTO(1,"F");
        when(orderService.updateOrder(any(),any())).thenReturn("Order 1 status successfully updated to F.");
        assertEquals(HttpStatus.OK, orderController.updateOrder(updateRequest).getStatusCode());
    }

    @Test
    @DisplayName("When create order, then return ok response")
    void whenUpdateOrderWithNonParentHouseUser_thenThrowException() {
        DecodeToken.location = 2;
        OrderUpdateRequestDTO updateRequest = new OrderUpdateRequestDTO(1,"F");
        assertThrows(ForbiddenAccessException.class, () -> orderController.updateOrder(updateRequest).getStatusCode());
    }

    private static <T> T getObject(String filePath, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(ResourceUtils.getFile(filePath), objectMapper .getTypeFactory().constructType(target));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
