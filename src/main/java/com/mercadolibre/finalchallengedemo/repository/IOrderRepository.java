package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderItemEntity, Integer> {

    //get subsidiary id by country name
    //get dealer id with dealerNumber
    //get order_number (order_id) with the above
    //get all order_details
    @Query("FROM OrderItemEntity orderItem" +
            " LEFT JOIN DealerOrderEntity dealerOrder ON dealerOrder.orderNumber = orderItem.id" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE orderItem.correspondingOrder.dealerId = :dealerNumber")
    List<OrderItemEntity> getOrderItemsByDealer(Integer dealerNumber);


    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber")
    List<DealerOrderEntity> getDealerOrdersByDealer(Integer dealerNumber);

}
