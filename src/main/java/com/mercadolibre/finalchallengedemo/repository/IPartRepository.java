package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.PartsResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartsResponseEntity, Integer> {

    /*Get parts that where modified from the query date to the current date (p)*/
    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ")
    List<PartsResponseEntity> findPartsModifiedSinceDate(Date filterDate, Date currentDate);


    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartsResponseEntity> findPartsModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


    //TODO falta agregar en la tabla parts_modifications algun campo precio para que sea el actual y asi poder saber si es diferente al normal que ya existe en la tabla.
    @Query("FROM PartsResponseEntity p LEFT JOIN PartModificationEntity pm ON p.id = pm.part.partCode WHERE p.normalPrice <> pm.salePrice AND p.lastModification BETWEEN :filterDate AND :currentDate ")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDate(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p LEFT JOIN PartModificationEntity pm ON p.id = pm.part.partCode WHERE p.normalPrice <> pm.salePrice AND p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p LEFT JOIN PartModificationEntity pm ON p.id = pm.part.partCode WHERE p.normalPrice <> pm.salePrice AND p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    @Query("FROM PartsResponseEntity p LEFT JOIN PartModificationEntity pm ON p.id = pm.part.partCode WHERE p.normalPrice <> pm.salePrice AND p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartsResponseEntity> findPartsWithPriceModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


}
