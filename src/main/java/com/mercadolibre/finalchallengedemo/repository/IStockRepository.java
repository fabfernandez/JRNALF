package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.model.StockSubsidiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends JpaRepository<StockSubsidiaryEntity,Integer> {

    @Query("FROM StockSubsidiaryEntity s WHERE s.subsidiary.id = :idSubsidiary AND s.part.partCode = :partCode")
    StockSubsidiaryEntity findStockByPartCodeAndSubsidiary(Integer partCode, Integer idSubsidiary);
}
