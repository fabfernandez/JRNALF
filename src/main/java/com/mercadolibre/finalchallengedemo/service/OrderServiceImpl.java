package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        //List<OrderDetailEntity> response = orderRepository.getOrdersByDealerAndStatus(Integer.valueOf(dealerNumber));

        //List<PartOrderDetailDTO> detailDTOS = response.stream()
        //        .map(entity -> modelMapper.map(entity, PartOrderDetailDTO.class)).collect(Collectors.toList());


        List<DealerOrderEntity> queryResult =
                orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber));

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
