package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.OrderUpdateRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.exceptions.ForbiddenAccessException;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parts/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    //REQ 2
    @GetMapping()
    public ResponseEntity<DealerOrderResponseDTO> getOrdersByDealerAndStatus(
            @Validated PartOrderQueryParamsDTO filter){
            return ResponseEntity.ok(orderService.process(filter));
    }

    //REQ 3 Endpoint
    @GetMapping("/{orderNumberCM}")
    public ResponseEntity<OrderStatusResponseDTO> getOrdersFromDealersStatus(
            @Validated @PathVariable(value = "orderNumberCM") String orderNumber){
        OrderStatusQueryParamsDTO orderStatusCM = new OrderStatusQueryParamsDTO(orderNumber);
        return ResponseEntity.ok(orderService.getOrdersFromDealersStatus(orderStatusCM));
    }

    //REQ 5 Endpoint
    @PostMapping
    public ResponseEntity createOrder(@Validated @RequestBody OrderRequestDTO order) {
        if(DecodeToken.location == 1)
            throw new ForbiddenAccessException("'Casa Matriz' users are not allowed.");
        return ResponseEntity.ok(this.orderService.createOrder(order));
    }

    @GetMapping("/dealers")
    public ResponseEntity<List<DealerOrdersDTO>> getAllOrders(){
        if(DecodeToken.location != 1)
            throw new ForbiddenAccessException("'Only casa matriz users are allowed.");
        else
            return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/update_status")
    public ResponseEntity updateOrder(@Validated @RequestBody OrderUpdateRequestDTO orderUpdate) {
        if(DecodeToken.location != 1)
            throw new ForbiddenAccessException("Only 'Casa Matriz' user allowed.");
        return ResponseEntity.ok(this.orderService.updateOrder(orderUpdate.getOrderNumber(),orderUpdate.getStatusCode().charAt(0)));
    }
}
