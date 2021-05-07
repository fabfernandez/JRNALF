package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import com.mercadolibre.finalchallengedemo.entities.PartsResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartsResponseEntity, Integer> {

   @Query(" FROM PartsResponseEntity p LEFT JOIN StockSubsidiaryEntity s ON s.subsidiary.id = :idSubsidiary WHERE s.part.partCode = p.partCode and p.lastModification BETWEEN :filterDate AND :currentDate ")
    List<PartsResponseEntity> findPartsModifiedSinceDate(Date filterDate, Date currentDate, Integer idSubsidiary);


    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


    @Query("FROM PartsResponseEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDate(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastPriceModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


}
