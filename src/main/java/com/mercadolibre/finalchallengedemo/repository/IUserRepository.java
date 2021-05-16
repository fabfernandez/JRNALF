package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,String> {
    @Query("SELECT t from UserEntity t WHERE t.username = :username and t.password = :password")
    UserEntity getByUser(String username, String password);
}
