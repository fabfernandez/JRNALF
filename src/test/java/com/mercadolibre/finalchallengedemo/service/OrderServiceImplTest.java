package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private final OrderServiceImpl orderService;

    private final IOrderRepository repository = Mockito.mock(IOrderRepository.class);

    @Autowired
    public OrderServiceImplTest(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @Test
    void getOrdersByDealerAndStatus() {

    }
}