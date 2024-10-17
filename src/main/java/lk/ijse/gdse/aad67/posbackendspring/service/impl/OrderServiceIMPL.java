package lk.ijse.gdse.aad67.posbackendspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad67.posbackendspring.dao.CustomerDAO;
import lk.ijse.gdse.aad67.posbackendspring.dao.OrderDAO;
import lk.ijse.gdse.aad67.posbackendspring.dao.OrderItemDAO;
import lk.ijse.gdse.aad67.posbackendspring.dto.OrderDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderItemEntity;
import lk.ijse.gdse.aad67.posbackendspring.service.OrderService;
import lk.ijse.gdse.aad67.posbackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    Mapping mapping;

    @Override
    public void saveOrder(OrderDTO orderDTO) {

        CustomerEntity customer = customerDAO.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        OrderEntity orderEntity = mapping.toOrderEntityWithCustomer(orderDTO, customer);


        List<OrderItemEntity> orderItemEntities = mapping.toOrderItemEntities(orderDTO.getOrderItems(), orderEntity);


        orderEntity.setOrderItems(orderItemEntities);


        orderDAO.save(orderEntity);
    }

}
