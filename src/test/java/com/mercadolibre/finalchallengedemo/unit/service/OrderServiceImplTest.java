package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.OrderUpdateRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.entities.*;
import com.mercadolibre.finalchallengedemo.exceptions.CanNotUpdateException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.NoStockException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.*;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private IOrderRepository orderRepository;
    @Mock
    private ISubsidiaryOrderRepository subsidiaryOrderRepository;
    @Mock
    private IPartRepository partRepository;
    @Mock
    private ISubsidiaryOrderItemRepository subsidiaryOrderItemRepository;
    @Mock
    private IStockRepository stockRepository;

    private OrderServiceImpl orderService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static DealerOrderResponseDTO dealerOrderResponseDTO;
    private static List<DealerOrderEntity> dealerOrders;
    private static List<PartEntity> parts;
    private static DealerOrderEntity dealerOrder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.orderService = new OrderServiceImpl(orderRepository, new ModelMapper(),subsidiaryOrderRepository,partRepository,subsidiaryOrderItemRepository,stockRepository);
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
        dealerOrder = dealerOrders.get(0);
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
        //order 3 is not valid
        Assertions.assertThrows(InvalidOrderFilterException.class,
                () -> orderService.getOrders("3", null, 1, 3));
    }

    @Test
    @DisplayName("When dealer number and order 1 are received, token from Casa Matriz then get all corresponding orders.")
    void getDealerOrdersByDealerAsc() {
        when(orderRepository.getDealerOrdersByDealerAsc(any(), any()))
                .thenReturn(dealerOrders);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.getOrders("3", null, 1, 1);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When dealer number and order 2 are received, token from Casa Matriz then get all corresponding orders.")
    void getDealerOrdersByDealerDesc() {
        when(orderRepository.getDealerOrdersByDealerDesc(any(), any()))
                .thenReturn(dealerOrders);

        //using the other method to test
        PartOrderQueryParamsDTO params = new PartOrderQueryParamsDTO();
        params.setDealerNumber("3");
        params.setOrder(2);

        DealerOrderResponseDTO orderResponseDTO =
                orderService.process(params);

        Assertions.assertEquals(dealerOrderResponseDTO, orderResponseDTO);
    }

    @Test
    @DisplayName("When no orders are found then throw exception.")
    void noOrdersFound() {
        when(orderRepository.getDealerOrdersByDealerAsc(any(), any()))
                .thenReturn(null);

        Assertions.assertThrows(PartsNotFoundException.class,
                () -> orderService.getOrders("3", "F", 1, 1));
    }

    @Test
    @DisplayName("When order number return corresponding order and details")
    void getOrdersFromDealersStatus() throws IOException {

        when(orderRepository.getOrder(any(),any()))
                .thenReturn(dealerOrder);

        OrderStatusResponseDTO response = objectMapper.readValue(new File("src/test/resources/orderByOrderNumber.json"),
                new TypeReference<>() {
                });

        OrderStatusQueryParamsDTO request = new OrderStatusQueryParamsDTO();
        request.setOrderNumberCM("0000-0000-00000003");
        DecodeToken.location = 1;
        OrderStatusResponseDTO resp2 = orderService.getOrdersFromDealersStatus(request);


        Assertions.assertEquals(response,resp2);


    }

    @Test
    @DisplayName("When order number not found , throws exception.")
    void orderNotFound() {

        when(orderRepository.getOrder(any(), any()))
                .thenReturn(null);

        OrderStatusQueryParamsDTO request = new OrderStatusQueryParamsDTO();
        request.setOrderNumberCM("0000-0000-00000003");


        Assertions.assertThrows(PartsNotFoundException.class,
                () -> orderService.getOrdersFromDealersStatus(request));

    }


    @Test
    @DisplayName("When create order with stock available for the same, then return order created")
    void whenCreateOrderWithStockAvailableForTheSame_thenReturnOrderCreated() {
        OrderRequestDTO orderRequestDTO  = getObject("classpath:createOrderRequest.json",OrderRequestDTO.class);
        Set<SubsidiaryOrderItemsEntity> orderItemsEntities = new HashSet<>();
        StockSubsidiaryEntity stockSubsidiaryEntity = new StockSubsidiaryEntity(10,
            new PartEntity(1,"test","test","A1",1,1,1,1,1,1, Date.from(Instant.now().minus(100, ChronoUnit.DAYS)),null,null),
            new SubsidiaryEntity(1,"test","test",1,"test",null)
        );
        SubsidiaryOrderEntity subsidiaryOrderEntity = new SubsidiaryOrderEntity(1,Date.from(Instant.now()),'P',4,Date.from(Instant.now().plus(7,ChronoUnit.DAYS)),0,orderItemsEntities);
        when(stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stockSubsidiaryEntity);
        when(subsidiaryOrderRepository.save(any())).thenReturn(subsidiaryOrderEntity);
        when(subsidiaryOrderItemRepository.save(any())).thenReturn(new SubsidiaryOrderItemsEntity());


        OrderDetailsDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);

        Assertions.assertNotNull(orderResponseDTO);
        Assertions.assertEquals(1,orderResponseDTO.getOrderNumber());

    }


    @Test
    @DisplayName("When create order with non stock available for the same, then throw no stock exception")
    void whenCreateOrderWithNonStockAvailableForTheSame_thenThrowNoStockException() {
        OrderRequestDTO orderRequestDTO  = getObject("classpath:createOrderRequest.json",OrderRequestDTO.class);
        Set<SubsidiaryOrderItemsEntity> orderItemsEntities = new HashSet<>();
        StockSubsidiaryEntity stockSubsidiaryEntity = new StockSubsidiaryEntity(0,
                new PartEntity(1,"test","test","A1",1,1,1,1,1,1, Date.from(Instant.now().minus(100, ChronoUnit.DAYS)),null,null),
                new SubsidiaryEntity(1,"test","test",1,"test",null)
        );
        SubsidiaryOrderEntity subsidiaryOrderEntity = new SubsidiaryOrderEntity(1,Date.from(Instant.now()),'P',4,Date.from(Instant.now().plus(7,ChronoUnit.DAYS)),0,orderItemsEntities);
        when(stockRepository.findStockByPartCodeAndSubsidiary(any(),any())).thenReturn(stockSubsidiaryEntity);
        when(subsidiaryOrderRepository.save(any())).thenReturn(subsidiaryOrderEntity);
        when(subsidiaryOrderItemRepository.save(any())).thenReturn(new SubsidiaryOrderItemsEntity());

        Assertions.assertThrows(NoStockException.class, () -> orderService.createOrder(orderRequestDTO));
    }


    @Test
    @DisplayName("When update successfully a order, then return the order updated")
    void whenUpdateSuccessfullyAOrder_thenReturnTheOrderUpdated() {
        ModelMapper mapper = new ModelMapper();
        OrderDetailsDTO order = getObject("classpath:subsidiaryOrder.json",OrderDetailsDTO.class);
        SubsidiaryOrderEntity orderEntity = mapper.map(order,SubsidiaryOrderEntity.class);
        orderEntity.setOrderStatus('P');
        orderEntity.setSubsidiaryId(2);
        Set<SubsidiaryOrderItemsEntity> orderItemsEntities = new HashSet<>();
        PartEntity part = new PartEntity();
        part.setPartCode(1);
        SubsidiaryOrderItemsEntity orderItem = new SubsidiaryOrderItemsEntity();
        orderItem.setSubsidiaryOrder(orderEntity);
        orderItem.setPart(part);
        orderItem.setQuantity(5);
        StockSubsidiaryEntity stockSubsidiaryEntityParentHouse = new StockSubsidiaryEntity(10,
                new PartEntity(1,"test","test","A1",1,1,1,1,1,1, Date.from(Instant.now().minus(100, ChronoUnit.DAYS)),null,null),
                new SubsidiaryEntity(1,"test","test",1,"test",null)
        );when(subsidiaryOrderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        StockSubsidiaryEntity stockSubsidiaryEntityCentralHouse = new StockSubsidiaryEntity(0,
                new PartEntity(1,"test","test","A1",1,1,1,1,1,1, Date.from(Instant.now().minus(100, ChronoUnit.DAYS)),null,null),
                new SubsidiaryEntity(2,"test","test",1,"test",null)
        );
        Set<SubsidiaryOrderItemsEntity> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        orderEntity.setOrderDetails(orderItems);
        when(stockRepository.findStockByPartCodeAndSubsidiary(1,0)).thenReturn(stockSubsidiaryEntityParentHouse);
        when(stockRepository.findStockByPartCodeAndSubsidiary(1,2)).thenReturn(stockSubsidiaryEntityCentralHouse);
        when(subsidiaryOrderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        when(subsidiaryOrderRepository.save(any())).thenReturn(orderEntity);
        when(subsidiaryOrderItemRepository.save(any())).thenReturn(new SubsidiaryOrderItemsEntity());
        String responseMsg = "Order 14 status successfully updated to F";
        Assertions.assertEquals(responseMsg,orderService.updateOrder(1,'F'));

    }

    @Test
    @DisplayName("When update order with the same status, then throw Exception")
    void whenUpdateOrderWithTheSameStatus_thenThrowException() {
        ModelMapper mapper = new ModelMapper();
        OrderDetailsDTO order = getObject("classpath:subsidiaryOrder.json",OrderDetailsDTO.class);
        SubsidiaryOrderEntity orderEntity = mapper.map(order,SubsidiaryOrderEntity.class);
        orderEntity.setOrderStatus('P');
        when(subsidiaryOrderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        when(subsidiaryOrderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        Assertions.assertThrows(CanNotUpdateException.class, () -> orderService.updateOrder(1,'P'));
    }





    private <T> T getList(String filePath, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(ResourceUtils.getFile(filePath), objectMapper .getTypeFactory().constructCollectionType(List.class, Class.forName(target.getName())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T getObject(String filePath, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(ResourceUtils.getFile(filePath), objectMapper .getTypeFactory().constructType(target));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}