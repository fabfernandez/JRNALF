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
public interface IOrderRepository extends JpaRepository<OrderDetailEntity, Integer> {

    //get subsidiary id by country name
    //get dealer id with dealerNumber
    //get order_number (order_id) with the above
    //get all order_details
    @Query("FROM OrderDetailEntity orderItem" +
            " LEFT JOIN DealerOrderEntity dealerOrder ON dealerOrder.orderNumber = orderItem.idOrderDetail" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE orderItem.dealerOrderEntityID.dealerId = :dealerNumber")
    List<OrderDetailEntity> getOrdersByDealerAndStatus(Integer dealerNumber);
}
