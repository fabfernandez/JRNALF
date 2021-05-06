package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import com.mercadolibre.finalchallengedemo.service.IOrderService;
import com.mercadolibre.finalchallengedemo.service.OrderServiceImpl;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parts/orders")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/")
    public ResponseEntity<PartOrderQueryParamsDTO> getOrdersByDealerAndStatus(
            @Validated @RequestParam PartOrderQueryParamsDTO filter){
            return ResponseEntity.ok(orderService.getOrders(filter));
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
