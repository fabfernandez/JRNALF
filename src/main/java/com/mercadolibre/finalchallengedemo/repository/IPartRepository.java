package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.model.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartEntity, Integer> {


    //This hql applies for the query type P and will return all the parts modified since the date requested to the current date.
    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ")
    List<PartEntity> findPartsModifiedSinceDate(Date filterDate, Date currentDate);

    //This hql applies for the query type P and will return all the parts modified since the date requested to the current date, sorted by description asc.
    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartEntity> findPartsModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    //This hql applies for the query type P and will return all the parts modified since the date requested to the current date, sorted by description desc.
    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartEntity> findPartsModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    //This hql applies for the query type P and will return all the parts modified since the date requested to the current date, sorted by the last modification date desc.
    @Query("FROM PartEntity p WHERE p.lastModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastModification DESC")
    List<PartEntity> findPartsModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);

    //This hql applies for the query type V and will return all the parts with price modified since the date requested to the current date.
    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ")
    List<PartEntity> findPartsWithPriceModifiedSinceDate(Date filterDate, Date currentDate);

    //This hql applies for the query type V and will return all the parts with price modified since the date requested to the current date, sorted by description asc.
    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description ASC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionAsc(Date filterDate, Date currentDate);

    //This hql applies for the query type V and will return all the parts with price modified since the date requested to the current date, sorted by description desc.
    @Query("FROM PartEntity p WHERE p.lastPriceModification  BETWEEN :filterDate AND :currentDate ORDER BY p.description DESC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByDescriptionDesc(Date filterDate, Date currentDate);

    //This hql applies for the query type V and will return all the parts with price modified since the date requested to the current date, sorted by the last price modification date desc.
    @Query("FROM PartEntity p WHERE p.lastPriceModification BETWEEN :filterDate AND :currentDate ORDER BY p.lastPriceModification DESC")
    List<PartEntity> findPartsWithPriceModifiedSinceDateSortedByLastModified(Date filterDate, Date currentDate);


}
