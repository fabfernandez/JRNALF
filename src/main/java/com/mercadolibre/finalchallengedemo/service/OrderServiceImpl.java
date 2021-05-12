package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import com.mercadolibre.finalchallengedemo.dtos.response.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.entities.*;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidOrderFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.NoStockException;
import com.mercadolibre.finalchallengedemo.exceptions.OrderNotFoundException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.*;
import com.mercadolibre.finalchallengedemo.security.DecodeToken;
import com.mercadolibre.finalchallengedemo.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final ISubsidiaryOrderRepository subsidiaryOrderRepository;
    private final ModelMapper modelMapper;
    private final IPartRepository partRepository;
    private final ISubsidiaryOrderItemRepository subsidiaryOrderItemRepository;
    private final IStockRepository stockRepository;

    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository, ModelMapper modelMapper, ISubsidiaryOrderRepository subsidiaryOrderRepository, IPartRepository partRepository, ISubsidiaryOrderItemRepository subsidiaryOrderItemRepository, IStockRepository stockRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.subsidiaryOrderRepository = subsidiaryOrderRepository;
        this.partRepository = partRepository;
        this.subsidiaryOrderItemRepository = subsidiaryOrderItemRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public DealerOrderResponseDTO getOrdersByDealerNumber(String dealerNumber,
                                                          String deliveryStatus,
                                                          Integer country, Integer order) {
        List<DealerOrderEntity> orderEntities = new ArrayList<>();

        //get all orders from a dealer
        if (dealerNumber != null && deliveryStatus == null && order == null)
            orderEntities = orderRepository.getDealerOrdersByDealer(Integer.valueOf(dealerNumber), country);
        if (dealerNumber != null && deliveryStatus != null & order == null)
            orderEntities = orderRepository.getDealerOrdersByDealerOrderStatus(Integer.valueOf(dealerNumber), deliveryStatus, country);
        if (dealerNumber != null && deliveryStatus != null && order != null) {
            if (order.equals(1)) {
                orderEntities = orderRepository.getDealerOrdersByDealerStatusOrderedAsc(Integer.valueOf(dealerNumber), deliveryStatus, country);
            } else if (order.equals(2)) {
                orderEntities = orderRepository.getDealerOrdersByDealerStatusOrderedDesc(Integer.valueOf(dealerNumber), deliveryStatus, country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }
        if (dealerNumber != null && deliveryStatus == null && order != null) {
            if (order.equals(1)) {
                orderEntities = orderRepository.getDealerOrdersByDealerAsc(Integer.valueOf(dealerNumber), country);
            } else if (order.equals(2)) {
                orderEntities = orderRepository.getDealerOrdersByDealerDesc(Integer.valueOf(dealerNumber), country);
            } else
                throw new InvalidOrderFilterException("Order selected is not valid");
        }


        //configurando modelmapper
        if (modelMapper.getTypeMap(DealerOrderItems.class, PartOrderDetailDTO.class) == null) {
            TypeMap<DealerOrderItems, PartOrderDetailDTO> typeMap = modelMapper.createTypeMap(DealerOrderItems.class, PartOrderDetailDTO.class);


            typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getPart().getDescription(),
                    PartOrderDetailDTO::setDescription));

            typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getAccountType(),
                    PartOrderDetailDTO::setAccountType));

            typeMap.addMappings(mapper -> mapper.map(itemEntity -> itemEntity.getReason(),
                    PartOrderDetailDTO::setReason));
        }
        //build orderDTOs
        List<OrderDetailsDTO> orders =
                orderEntities
                        .stream()
                        .map(orderEntity -> modelMapper.map(orderEntity,
                                OrderDetailsDTO.class))
                        .collect(Collectors.toList());

        //validate if orders is null or empty
        if (orders.size() == 0 || orders == null) {
            throw new PartsNotFoundException("Parts not found");
        }

        //build response
        return new DealerOrderResponseDTO(
                Integer.valueOf(dealerNumber),
                orders
        );

    }

    @Override
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO
                                                                     orderStatusCMDTO) {
        String[] queryArray = orderStatusCMDTO.getOrderNumberCM().split("-");
        String dealer = queryArray[0];
        String orderNumber = queryArray[1];
        OrderResponseDTO response = new OrderResponseDTO();

        // Get orders that matches subsidiary and order number.
        List<DealerOrderEntity> orderEntities = orderRepository.getOrdersFromDealersStatus(Integer.valueOf(orderNumber), Integer.valueOf(dealer));

        // Setting response values.
        response.setOrderNumberCE(dealer);

        /*
        Setear desde el resultado de la query orderEntities:
            response.setOrderDate();
            response.setOrderStatus();

        Setear orderDetails que ya lo hizo Fabri:
            response.setOrderDetails();
         */
        return null;
    }


    @Override
    public DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params) {

        String dealerNumber = params.getDealerNumber();
        String deliveryStatus = params.getDeliveryStatus();
        Integer order = params.getOrder();
        Integer country = DecodeToken.location;

        //send data to method that evaluates params to make
        return getOrdersByDealerNumber(dealerNumber, deliveryStatus, country, order);
    }

    @Override
    public OrderDetailsDTO createOrder(OrderRequestDTO order) {
        SubsidiaryOrderEntity newSubsidiaryOrder = generateSubsidiaryOrderEntity(order);
        validateStockAvailable(newSubsidiaryOrder);
        SubsidiaryOrderEntity subsidiaryOrderCreated = this.subsidiaryOrderRepository.save(newSubsidiaryOrder);

        //Dentro del for se va a crear cada subsidiaryOrderItem, seteandole antes, la subsidiaryOrder creada y asi cumplir con la relacion de ambas entidades.
        for (SubsidiaryOrderItemsEntity s : subsidiaryOrderCreated.getOrderDetails()) {
            s.setSubsidiaryOrder(subsidiaryOrderCreated);
            this.subsidiaryOrderItemRepository.save(s);
        }

        return modelMapper.map(subsidiaryOrderCreated,OrderDetailsDTO.class);

    }

    //Returns a subsidiary order entity from a request, adding default values
    private SubsidiaryOrderEntity generateSubsidiaryOrderEntity(OrderRequestDTO order) {
        SubsidiaryOrderEntity subsidiaryOrder = modelMapper.map(order,SubsidiaryOrderEntity.class);
        subsidiaryOrder.setOrderDate(Date.from(Instant.now()));
        subsidiaryOrder.setDeliveryDate(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)));
        subsidiaryOrder.setDaysDelay(0);
        subsidiaryOrder.setOrderStatus('P');
        subsidiaryOrder.setSubsidiaryId(DecodeToken.location);
        return subsidiaryOrder;
    }

    @Override
    public void updateOrder(Integer orderNumber, Character orderStatus) {
        SubsidiaryOrderEntity order = findSubsidiaryOrder(orderNumber);
        ValidatorUtil.validateOrderUpdate(order.getOrderStatus(),orderStatus);

        if (orderStatus.equals('F'))
            finalizeOrder(order);

        order.setOrderStatus(orderStatus);
        subsidiaryOrderRepository.save(order);
    }


    private void finalizeOrder(SubsidiaryOrderEntity order) {
        for ( SubsidiaryOrderItemsEntity orderItem :order.getOrderDetails()) {
            updateStocks(
                    orderItem.getSubsidiaryOrder().getSubsidiaryId(),
                    orderItem.getPart().getPartCode(),
                    orderItem.getQuantity()
            );
        }
    }

    private void updateStocks(Integer subsidiaryId, Integer partCode, Integer quantity) {
        StockSubsidiaryEntity parentHouseStock = stockRepository.findStockByPartCodeAndSubsidiary(partCode, DecodeToken.location);
        StockSubsidiaryEntity centralHouseStock = stockRepository.findStockByPartCodeAndSubsidiary(partCode, subsidiaryId);
        Integer parentHouseQuantity = parentHouseStock.getQuantity();
        Integer centralHouseQuantity = centralHouseStock.getQuantity();
        parentHouseStock.setQuantity(parentHouseQuantity - quantity);
        centralHouseStock.setQuantity(centralHouseQuantity + quantity);
        stockRepository.save(parentHouseStock);
        stockRepository.save(centralHouseStock);
    }

    //Before create a order the reserved stock is verified. By reserved stock refers to the stock of the parent company minus the total quantity of each part in pending orders
    private void validateStockAvailable(SubsidiaryOrderEntity order) {
        String partCodesWithoutStock = "";
        Integer partCode;
        Integer quantity;
        for (SubsidiaryOrderItemsEntity orderItem: order.getOrderDetails()) {
            partCode = orderItem.getPart().getPartCode();
            quantity = orderItem.getQuantity();
            if(getAvailableStockFromParentHouseByPartCode(partCode) - quantity < 0)
                partCodesWithoutStock += partCode + ",";

        }
        if(!partCodesWithoutStock.equals(""))
            throw new NoStockException("Parts with code: " + partCodesWithoutStock + " don't have stock");
    }


    //Returns the stock reserved available from the parent house
    private Integer getAvailableStockFromParentHouseByPartCode(Integer partCode) {
        StockSubsidiaryEntity parentHouseStock = stockRepository.findStockByPartCodeAndSubsidiary(partCode,1);
        return parentHouseStock.getQuantity() - getOrderItemTotalQuantityByPartCode(partCode);
    }

    //Returns all the items from orders which are in status P or D
    private List<SubsidiaryOrderItemsEntity> getAllPendingOrderItems() {
        List<SubsidiaryOrderEntity> pendingOrders = subsidiaryOrderRepository.findPendingOrders(); // All pending orders with status P or D.
        List<Integer> orderItemIdsList = new ArrayList<>();
        pendingOrders.forEach(o -> orderItemIdsList.addAll(o.getOrderDetails().stream().mapToInt(i -> i.getId()).boxed().collect(Collectors.toList()))); // All the items id from the above orders
        List<SubsidiaryOrderItemsEntity> orderItemList = subsidiaryOrderItemRepository.findAllById(orderItemIdsList);// All the items from the above id list
        return orderItemList;
    }

    // Total quantity from partCode in pending orders
    private Integer getOrderItemTotalQuantityByPartCode(Integer partCode) {
        List<SubsidiaryOrderItemsEntity> orderItemsByPartCode = getAllPendingOrderItems().stream().filter( orderItem -> orderItem.getPart().getPartCode() == partCode).collect(Collectors.toList());
        return orderItemsByPartCode.stream().mapToInt(orderItem -> orderItem.getQuantity()).sum();
    }


    private SubsidiaryOrderEntity findSubsidiaryOrder(Integer id) {
        Optional<SubsidiaryOrderEntity> order = this.subsidiaryOrderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("No order found with id " + id);
        return order.get();
    }
}
