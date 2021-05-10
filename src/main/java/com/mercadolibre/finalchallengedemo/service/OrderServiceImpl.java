package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.dtos.response.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.OrderItemEntity;
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
    }

    @Override
    public DealerOrderResponseDTO getOrdersByDealerNumber(String dealerNumber,
                                                          String deliveryStatus,
                                                          Integer country, Integer order) {
        List<DealerOrderEntity> orderEntities = new ArrayList<>();

        //get all orders from a dealer
        if (dealerNumber != null && deliveryStatus == null && order == null)
            orderEntities = orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber),country);
        if (dealerNumber != null && deliveryStatus != null & order == null)
            orderEntities = orderRepository.getDealerOrdersByDealerOrderStatus(Integer.valueOf(dealerNumber), deliveryStatus,country);
        if(dealerNumber != null && deliveryStatus != null && order != null){
            if(order.equals(1)){
                orderEntities = orderRepository.getDealerOrdersByDealerStatusOrderedAsc(Integer.valueOf(dealerNumber), deliveryStatus,country);
            } else if(order.equals(2)){
                orderEntities = orderRepository.getDealerOrdersByDealerStatusOrderedDesc(Integer.valueOf(dealerNumber), deliveryStatus, country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }
        if (dealerNumber != null && deliveryStatus == null && order != null){
            if(order.equals(1)){
                orderEntities = orderRepository.getDealerOrdersByDealerAsc(Integer.valueOf(dealerNumber),country);
            } else if(order.equals(2)){
                orderEntities = orderRepository.getDealerOrdersByDealerDesc(Integer.valueOf(dealerNumber),country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }



        //configurando modelmapper
        if(modelMapper.getTypeMap(OrderItemEntity.class, PartOrderDetailDTO.class) == null){
            TypeMap<OrderItemEntity, PartOrderDetailDTO> typeMap = modelMapper.createTypeMap(OrderItemEntity.class, PartOrderDetailDTO.class);


            typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getPart().getDescription(),
                    PartOrderDetailDTO::setDescription));

        typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getAccountType(),
                PartOrderDetailDTO::setAccountType));

        typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getReason(),
                PartOrderDetailDTO::setReason));
            }
        //build orderDTOs
        List<OrderDetailsDTO> orders =
                orderEntities
                        .stream()
                        .map(orderEntity -> modelMapper.map(orderEntity,
                                OrderDetailsDTO.class))
                        .collect(Collectors.toList());

        //validate if orders is null or empty
        if(orders.size() == 0 || orders == null){
            throw new PartsNotFoundException("Parts not found");
        }

        //build response
        return new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                orders
        );

    }

    @Override
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO
                                                                         orderStatusCMDTO) {
        String[] queryArray = orderStatusCMDTO.getOrderNumberCM().split("-");
        String dealer = queryArray[0];
        String orderNumber = queryArray[1];
        OrderResponseDTO response = new OrderResponseDTO();

        // Get orders that matches subsidiary and order number.
        List<DealerOrderEntity> orderEntities = orderRepository.getOrdersFromDealersStatus(Integer.valueOf(orderNumber), Integer.valueOf(dealer));

        // Setting response values.
        response.setOrderNumberCE(dealer);

        /*
        Setear desde el resultado de la query orderEntities:
            response.setOrderDate();
            response.setOrderStatus();

        Setear orderDetails que ya lo hizo Fabri:
            response.setOrderDetails();
         */
        return null;
    }


    @Override
    public DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params) {

        String dealerNumber = params.getDealerNumber();
        String deliveryStatus = params.getDeliveryStatus();
        Integer order = params.getOrder();
        Integer country = DecodeToken.location;

        //send data to method that evaluates params to make
        return getOrdersByDealerNumber(dealerNumber, deliveryStatus, country, order);
    }
}
