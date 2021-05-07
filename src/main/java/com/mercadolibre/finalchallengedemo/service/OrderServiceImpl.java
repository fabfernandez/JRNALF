package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
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
