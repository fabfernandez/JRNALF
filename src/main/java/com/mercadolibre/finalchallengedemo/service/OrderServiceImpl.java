package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private ModelMapper modelMapper;

    public OrderServiceImpl(IOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    private DealerOrderResponseDTO getOrdersByDealerAndStatus(String dealerNumber,
                                                              String deliveryStatus,
                                                              String country) {
        //get subsidiary id by country name

        //get dealer id with dealerNumber

        //get order_number (order_id) with the above
        //get all order_details
        orderRepository.getOrdersByDealerAndStatus(dealerNumber, deliveryStatus, country);

        //build response

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
