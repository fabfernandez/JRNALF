package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderItems;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;

        //CONFIGURANDO MODEL MAPPER
        //DealerOrderItems -> OrderItemDTO
        TypeMap<DealerOrderItems, OrderItemDTO> typeMap1
                = modelMapper.createTypeMap(DealerOrderItems.class, OrderItemDTO.class);
        typeMap1.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getPart().getDescription(),
                OrderItemDTO::setDescription));
        typeMap1.addMappings(mapper -> mapper.map(DealerOrderItems::getAccountType,
                OrderItemDTO::setAccountType));
        typeMap1.addMappings(mapper -> mapper.map(DealerOrderItems::getReason,
                OrderItemDTO::setReason));

        //DealerOrderItems -> OrderItemPartStatusDTO
        TypeMap<DealerOrderItems, OrderItemPartStatusDTO> typeMap3
                = modelMapper.createTypeMap(DealerOrderItems.class, OrderItemPartStatusDTO.class);
        typeMap3.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getPart().getDescription(),
                OrderItemPartStatusDTO::setDescription));

        //DealerOrderEntity -> OrderDetailsDTO
        TypeMap<DealerOrderEntity, OrderDetailsDTO> typeMap2
                = modelMapper.createTypeMap(DealerOrderEntity.class, OrderDetailsDTO.class);
        typeMap2.addMappings(mapper -> mapper.map(DealerOrderEntity::getOrderStatus,
                OrderDetailsDTO::setDeliveryStatus));

    }

    @Override
    //REQ 2
    public DealerOrderResponseDTO getOrders(String dealerNumber,
                                            String deliveryStatus,
                                            Integer country, Integer order) {
        List<DealerOrderEntity> orderEntities = new ArrayList<>();

        //get all orders from a dealer
        if (dealerNumber != null && deliveryStatus == null && order == null)
            orderEntities = orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber), country);
        if (dealerNumber != null && deliveryStatus != null && order == null)
            orderEntities = orderRepository.getDealerOrdersByNumberAndStatus(Integer.valueOf(dealerNumber), deliveryStatus, country);
        if (dealerNumber != null && deliveryStatus != null && order != null) {
            if (order.equals(1)) {
                orderEntities =
                        orderRepository.getDealerOrdersByDealerStatusOrderedAsc(Integer.valueOf(dealerNumber), deliveryStatus, country);
            } else if (order.equals(2)) {
                orderEntities =
                        orderRepository.getDealerOrdersByDealerStatusOrderedDesc(Integer.valueOf(dealerNumber), deliveryStatus, country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }
        if (dealerNumber != null && deliveryStatus == null && order != null) {
            if (order.equals(1)) {
                orderEntities = orderRepository.getDealerOrdersByDealerAsc(Integer.valueOf(dealerNumber), country);
            } else if (order.equals(2)) {
                orderEntities = orderRepository.getDealerOrdersByDealerDesc(Integer.valueOf(dealerNumber), country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }

        //build orderDTOs
        List<OrderDetailsDTO> orders =
                orderEntities
                        .stream()
                        .map(orderEntity -> modelMapper.map(orderEntity,
                                OrderDetailsDTO.class))
                        .collect(Collectors.toList());

        //validate if orders is null or empty
        if (orders.isEmpty()) {
            throw new PartsNotFoundException("No orders found.");
        }
        //build response
        return new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                orders
        );
    }

    //REQ 3
    @Override
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO
                                                                     orderStatusCMDTO) {
        String[] queryArray = orderStatusCMDTO.getOrderNumberCM().split("-");
        String orderNumber = queryArray[2].replaceAll("^0+", "");

        // Get orders that matches subsidiary and order number
        DealerOrderEntity dealerOrderEntity =
                orderRepository.getOrder(Integer.valueOf(orderNumber), DecodeToken.location);

        //build DTO
        if (dealerOrderEntity == null) {
            throw new PartsNotFoundException("Order Not Found.");
        }
        OrderStatusResponseDTO response = modelMapper.map(dealerOrderEntity, OrderStatusResponseDTO.class);
        response.setOrderNumberCE(queryArray[1] + "-" + queryArray[2]);

        return response;
    }


    @Override
    public DealerOrderResponseDTO process(PartOrderQueryParamsDTO params) {

        String dealerNumber = params.getDealerNumber();
        String deliveryStatus = params.getDeliveryStatus();
        Integer order = params.getOrder();
        Integer country = DecodeToken.location;


        //send data to method that evaluates params to make
        return getOrders(dealerNumber, deliveryStatus, country, order);
    }
}
