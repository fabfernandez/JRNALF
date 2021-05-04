package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
