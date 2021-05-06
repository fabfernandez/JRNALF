package com.mercadolibre.finalchallengedemo.repository;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderResponseDTO, Integer> {
}
