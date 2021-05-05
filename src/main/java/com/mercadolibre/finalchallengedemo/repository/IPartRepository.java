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
    @Query("SELECT p from PartEntity p FROM LEFT JOIN parts_modifications " +
            "ON parts.part_code = parts_modifications.id_part " +
            "WHERE parts_modifications.last_modification > :lastModification")
    List<PartEntity> findPartEntityByDate(@Param("lastModification")LocalDate date);
}
