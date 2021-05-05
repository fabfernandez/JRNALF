package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPartRepository extends JpaRepository<PartEntity, Long> {

    //@Query("select p from PartEntity")
    //List<PartEntity> findPartEntityByDate(@Param("")LocalDate date);
}
