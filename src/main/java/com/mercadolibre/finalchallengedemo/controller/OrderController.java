package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
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

    @GetMapping()
    public ResponseEntity<DealerOrderResponseDTO> getOrdersByDealerAndStatus(
            @Validated PartOrderQueryParamsDTO filter){
            return ResponseEntity.ok(orderService.getOrders(filter));
    }

    //REQ 3 Endpoint
    @GetMapping("/{orderNumberCM}")
    public ResponseEntity<OrderStatusResponseDTO> getOrdersFromDealersStatus(
            @Validated @PathVariable(value = "orderNumberCM") OrderStatusQueryParamsDTO orderStatusCM){
        //REQ 3 TODO do some more magic here
        return ResponseEntity.ok(orderService.getOrdersFromDealersStatus(orderStatusCM));
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
