package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.OrderDetailEntity;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DealerOrderResponseDTO getOrdersByDealerAndStatus(String dealerNumber,
                                                             String deliveryStatus,
                                                             String country) {

        List<OrderDetailEntity> items =
                orderRepository.getOrderItemsByDealerAndStatus(Integer.valueOf(dealerNumber));

        items.get(0).getCorrespondingOrder();

        List<OrderDetailsDTO> ordersDTOs =
                items.stream()
                        .map(item -> modelMapper.map(item.getCorrespondingOrder(), OrderDetailsDTO.class))
                        .collect(Collectors.toList());














/*
        List<PartOrderDetailDTO> detailDTOS = items.stream()
                .map(entity -> modelMapper.map(entity, PartOrderDetailDTO.class)).collect(Collectors.toList());


        List<DealerOrderEntity> ordersQueryResult =
                orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber));

        List<OrderDetailsDTO> ordersDTOs =
                ordersQueryResult.stream()
                        .map(order -> modelMapper.map(order, OrderDetailsDTO.class))
                        .collect(Collectors.toList());


 */
        //build response
        DealerOrderResponseDTO responseDTO = new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                null
        );

        return responseDTO;
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
            return getOrdersByDealerAndStatus(dealerNumber, deliveryStatus, country);
        }

        //return response


        return null;
    }
}
