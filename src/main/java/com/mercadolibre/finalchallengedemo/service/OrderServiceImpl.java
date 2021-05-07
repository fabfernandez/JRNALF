package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.OrderItemEntity;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                                                          String country) {

        //get all orders from a dealer
        List<DealerOrderEntity> orderEntities =
                orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber));

        //configurando modelmapper
        TypeMap<OrderItemEntity, PartOrderDetailDTO> typeMap
                = modelMapper.createTypeMap(OrderItemEntity.class, PartOrderDetailDTO.class);

        typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getPart().getDescription(),
                PartOrderDetailDTO::setDescription));

        typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getAccountType(),
                PartOrderDetailDTO::setAccountType));

        typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getReason(),
                PartOrderDetailDTO::setReason));

        //build orderDTOs
        List<OrderDetailsDTO> orders =
                orderEntities
                        .stream()
                        .map(orderEntity -> modelMapper.map(orderEntity,
                                OrderDetailsDTO.class))
                        .collect(Collectors.toList());

        //build response
        return new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                orders
        );

    }

    @Override
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO) {
        //TODO Implement this service
        return null;
    }


    @Override
    public DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params) {

        String dealerNumber = params.getDealerNumber();
        String deliveryStatus = params.getDeliveryStatus();
        Integer order = params.getOrder();
        String country = "Argentina";
        //todo receive this in params

        //choose what to do with received params
        if (dealerNumber != null &&
                deliveryStatus == null &&
                order == null) {
            return getOrdersByDealerNumber(dealerNumber, deliveryStatus, country);
        }

        //return response


        return null;
    }
}
