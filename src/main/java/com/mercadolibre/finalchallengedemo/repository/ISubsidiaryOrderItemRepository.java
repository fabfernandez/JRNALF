package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.SubsidiaryOrderEntity;
import com.mercadolibre.finalchallengedemo.entities.SubsidiaryOrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubsidiaryOrderItemRepository extends JpaRepository<SubsidiaryOrderItemsEntity, Integer> {
}
