package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.model.SubsidiaryOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubsidiaryOrderRepository extends JpaRepository<SubsidiaryOrderEntity,Integer> {
    @Query("FROM SubsidiaryOrderEntity order WHERE order.orderStatus LIKE 'P' OR order.orderStatus LIKE 'D'")
    List<SubsidiaryOrderEntity> findPendingOrders();


}
