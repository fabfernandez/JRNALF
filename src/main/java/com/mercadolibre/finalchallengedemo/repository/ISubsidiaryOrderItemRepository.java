package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.model.SubsidiaryOrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubsidiaryOrderItemRepository extends JpaRepository<SubsidiaryOrderItemsEntity, Integer> {
}
