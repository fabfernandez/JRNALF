package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartEntity, Long> {

    /*Get parts that were modified from the query date to the current date (p)*/
    @Query("SELECT p FROM PartEntity p INNER JOIN FETCH p.partModificationEntity pm " +
            "ON p.id = pm.id WHERE pm.last_modification <= :lastModification")
    List<PartEntity> findPartEntityByDate(@Param("lastModification")LocalDate date);

    /*Get the parts that varied in price from the date of the consultation to the current date (v)*/
    @Query("SELECT p FROM PartEntity p INNER JOIN FETCH p.partModificationEntity pm " +
            "ON p.id = pm.id WHERE pm.normalPrice >= :priceVariation AND pm.last_modification <= :lastModification") // Chequear condicion de consulta
    List<PartEntity> findPartEntityByPriceVaration(@Param("priceVariation") Integer price, @Param("lastModification") LocalDate date);


}
