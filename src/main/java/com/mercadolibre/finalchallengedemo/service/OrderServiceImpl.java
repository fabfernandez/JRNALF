package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.partsorders.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.OrderDetailsDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;
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

    private DealerOrderResponseDTO getOrdersByDealerAndStatus(String dealerNumber,
                                                              String deliveryStatus,
                                                              String country) {

        List<OrderDetailEntity> response = orderRepository.getOrdersByDealerAndStatus(Integer.valueOf(dealerNumber));

        //build response
        DealerOrderResponseDTO responseDTO = new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                response.stream().map(o -> modelMapper.map(o, OrderDetailsDTO.class)).collect(Collectors.toList())
        );

        return responseDTO;
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
