package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import com.mercadolibre.finalchallengedemo.repository.IPartRepository;
import com.mercadolibre.finalchallengedemo.repository.IStockRepository;
import com.mercadolibre.finalchallengedemo.service.IOrderService;
import com.mercadolibre.finalchallengedemo.service.OrderServiceImpl;
import com.mercadolibre.finalchallengedemo.service.PartServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private IOrderRepository orderRepository;

    private OrderServiceImpl orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       this.orderService = new OrderServiceImpl(orderRepository, new ModelMapper());

    }

    @Test
    void getOrdersByDealerNumber() throws IOException {
        DealerOrderResponseDTO dealerOrderResponseDTO = objectMapper.readValue(new File("src/test/resources/filteredOrders.json"), new TypeReference<DealerOrderResponseDTO>() {
        });
        when(orderService.getOrdersByDealerNumber("3",any(),1,any())).thenReturn(dealerOrderResponseDTO);
        DealerOrderResponseDTO orderResponseDTO = orderService.getOrdersByDealerNumber("3",null,1,null);

        Assertions.assertEquals(orderResponseDTO,dealerOrderResponseDTO);
    }

    @Test
    void getOrdersFromDealersStatus() {
    }

    @Test
    void getOrders() {
    }
}