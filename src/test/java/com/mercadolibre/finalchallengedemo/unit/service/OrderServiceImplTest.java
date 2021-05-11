package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderItems;
import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import com.mercadolibre.finalchallengedemo.service.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private IOrderRepository orderRepository;

    private OrderServiceImpl orderService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static DealerOrderResponseDTO dealerOrderResponseDTO;
    private static List<DealerOrderEntity> dealerOrders;
    private static List<PartEntity> parts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.orderService = new OrderServiceImpl(orderRepository, new ModelMapper());
    }

    @BeforeAll
    static void beforeAll() throws IOException {
        dealerOrderResponseDTO =
                objectMapper.readValue(new File("src/test/resources/filteredOrders.json"),
                        new TypeReference<>() {
                        });
        dealerOrders =
                objectMapper.readValue(new File("src/test/resources/orderEntities.json"),
                        new TypeReference<>() {
                        });
        parts =
                objectMapper.readValue(new File("src/test/resources/partEntities.json"),
                        new TypeReference<>() {
                        });
        //get single order
        DealerOrderEntity dealerOrder = dealerOrders.get(0);
        //extract items
        List<DealerOrderItems> dealerOrderItems =
                new ArrayList<>(dealerOrder.getOrderDetails());
        //Set parts
        dealerOrderItems.get(0).setPart(parts.get(0));
        dealerOrderItems.get(1).setPart(parts.get(1));
        //Set order
        dealerOrderItems.get(0).setCorrespondingOrder(dealerOrder);
        dealerOrderItems.get(1).setCorrespondingOrder(dealerOrder);
        //change to set
        dealerOrder.setOrderDetails(dealerOrderItems.stream().collect(Collectors.toSet()));
        //set the final object
        dealerOrders.set(0, dealerOrder);
    }

    @Test
    @DisplayName("When only dealer number is received and token from Casa Matriz then get all corresponding orders.")
    void getOrdersByDealerNumber() {

        when(orderRepository.getDealerOrdersByDealer(any(), any()))
                .thenReturn(dealerOrders);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.getOrders("3", null, 1, null);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When dealer number and delivery status are received and token from Casa Matriz then get all corresponding orders.")
    void getDealerOrdersByNumberAndStatus() {
        when(orderRepository.getDealerOrdersByNumberAndStatus(any(), any(), any()))
                .thenReturn(dealerOrders);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.getOrders("3", "F", 1, null);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When dealer number, delivery status and order 1 are received, token from Casa Matriz then get all corresponding orders.")
    void getDealerOrdersByDealerStatusOrderedAsc() {
        when(orderRepository.getDealerOrdersByDealerStatusOrderedAsc(any(), any(), any()))
                .thenReturn(dealerOrders);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.getOrders("3", "F", 1, 1);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When dealer number, delivery status and order 2 are received, token from Casa Matriz then get all corresponding orders.")
    void getDealerOrdersByDealerStatusOrderedDesc() {
        when(orderRepository.getDealerOrdersByDealerStatusOrderedDesc(any(), any(), any()))
                .thenReturn(dealerOrders);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.getOrders("3", "F", 1, 2);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When dealer number, delivery status and order 3 are received, token from Casa Matriz then exception.")
    void badOrder() {

        when(orderRepository.getDealerOrdersByDealerStatusOrderedDesc(any(), any(), any()))
                .thenReturn(dealerOrders);

        //order 3 is not valid
        Assertions.assertThrows(InvalidOrderFilterException.class,
                () -> orderService.getOrders("3", "F", 1, 3));
    }
}