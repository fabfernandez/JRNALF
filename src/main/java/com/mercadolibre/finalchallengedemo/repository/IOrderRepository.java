package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.DealerOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.DealerOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<DealerOrderItems, Integer> {



    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.orderNumber = :orderNumber" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId")
    DealerOrderEntity getOrder(Integer orderNumber, Integer subsidiaryId);


    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId")
    List<DealerOrderEntity> getDealerOrdersByDealer(Integer dealerNumber, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.orderStatus = :status" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId")
    List<DealerOrderEntity> getDealerOrdersByNumberAndStatus(Integer dealerNumber, String status, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.orderStatus = :status" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId" +
            " ORDER BY dealerOrder.orderDate ASC ")
    List<DealerOrderEntity> getDealerOrdersByDealerStatusOrderedAsc(Integer dealerNumber, String status, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.orderStatus = :status" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId" +
            " ORDER BY dealerOrder.orderDate DESC ")
    List<DealerOrderEntity> getDealerOrdersByDealerStatusOrderedDesc(Integer dealerNumber, String status, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId" +
            " ORDER BY dealerOrder.deliveryDate ASC ")
    List<DealerOrderEntity> getDealerOrdersByDealerAsc(Integer dealerNumber, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder" +
            " LEFT JOIN  DealerEntity dealer ON dealer.idDealer = dealerOrder.dealerId" +
            " LEFT JOIN SubsidiaryEntity subsidiary ON subsidiary.id = dealerOrder.subsidiaryId" +
            " WHERE dealerOrder.dealerId = :dealerNumber" +
            " AND dealerOrder.subsidiaryId = :subsidiaryId" +
            " ORDER BY dealerOrder.deliveryDate DESC ")
    List<DealerOrderEntity> getDealerOrdersByDealerDesc(Integer dealerNumber, Integer subsidiaryId);

    @Query("FROM DealerOrderEntity dealerOrder " +
            "WHERE dealerOrder.dealerId = :dealerNumber AND dealerOrder.subsidiaryId = :subsidiaryId")
    List<DealerOrderEntity> getOrdersFromDealersStatus(Integer dealerNumber, Integer subsidiaryId);
}
