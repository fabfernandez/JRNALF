package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.OrderUpdateRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.exceptions.ForbiddenAccessException;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(orderService.getOrders(filter));
    }

    //REQ 3 Endpoint
    @GetMapping("/{orderNumberCM}")
    public ResponseEntity<OrderStatusResponseDTO> getOrdersFromDealersStatus(
            @Validated @PathVariable(value = "orderNumberCM") OrderStatusQueryParamsDTO orderStatusCM){
        return ResponseEntity.ok(orderService.getOrdersFromDealersStatus(orderStatusCM));
    }

    //REQ 5 Endpoint
    @PostMapping
    public ResponseEntity createOrder(@Validated @RequestBody OrderRequestDTO order) {
        if(DecodeToken.location == 1)
            throw new ForbiddenAccessException("Only users for the central house are enabled");
        return ResponseEntity.ok(this.orderService.createOrder(order));
    }

    @PostMapping("/update_status")
    public ResponseEntity updateOrder(@Validated @RequestBody OrderUpdateRequestDTO orderUpdate) {
        if(DecodeToken.location != 1)
            throw new ForbiddenAccessException("Only users for parent company are enabled");
        this.orderService.updateOrder(orderUpdate.getOrderNumber(),orderUpdate.getStatusCode());
        return ResponseEntity.ok("Successfully updated");
    }


    /*
    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderResponseDTO order){
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order created.");
    }*/

    /*
    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderResponseDTO order){
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order updated.");
    }*/

}
