package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepository orderRepository;
    private ModelMapper modelMapper;

    public OrderServiceImpl(IOrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public OrderResponseDTO getAll() {
        return null;
    }

    @Override
    public void deleteOrder(Integer id) {

    }

    @Override
    public void saveOrder(PartDTO part) {

    }

    @Override
    public OrderResponseDTO findOrder(Integer id) {
        return null;
    }
}
