package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.OrderDetailEntity;
import com.mercadolibre.finalchallengedemo.entities.PartsResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderResponseDTO, Integer> {

    //get subsidiary id by country name
    //get dealer id with dealerNumber
    //get order_number (order_id) with the above
    //get all order_details
    @Query("FROM OrderDetailEntity orderItem" +
            " LEFT JOIN DealerOrderEntity order ON order.order_number = orderItem.id_order_detail" +
            " LEFT JOIN  DealerEntity dealer ON dealer.id_leader = order.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = order.subsidiaryId")
    List<OrderDetailEntity> getOrdersByDealerAndStatus(String dealerNumber,
                                                       String deliveryStatus,
                                                       String country);
}
