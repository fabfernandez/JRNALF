package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartEntity, Integer> {

   @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ")
    List<PartEntity> findPartsModifiedSinceDate(Date filterDate, Date currentDate);


    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartEntity> findPartsModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartEntity> findPartsModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartEntity> findPartsModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ")
    List<PartEntity> findPartsWithPriceModifiedSinceDate(Date filterDate, Date currentDate);

    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartEntity p WHERE p.lastPriceModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


}
