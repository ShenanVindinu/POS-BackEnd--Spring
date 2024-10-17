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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceIMPL.class);

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


        // Create a new OrderEntity object
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDTO.getId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setCustomer(customer);
        orderEntity.setCustomerName(orderDTO.getCustomerName());
        orderEntity.setCustomerSalary(orderDTO.getCustomerSalary());
        orderEntity.setCustomerAddress(orderDTO.getCustomerAddress());
        logger.info("OrderEntity created ");

        // Manually create OrderItemEntities and map them directly
        List<OrderItemEntity> orderItemEntities = orderDTO.getOrderItems().stream()
                .map(itemDTO -> {
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setItemId(itemDTO.getItemId());
                    orderItemEntity.setItemName(itemDTO.getItemName());
                    orderItemEntity.setItemPrice(itemDTO.getItemPrice()); // Assuming price is a String
                    orderItemEntity.setOrderQTY(itemDTO.getOrderQTY()); // Assuming quantity is a String
                    orderItemEntity.setTotal(itemDTO.getTotal()); // Assuming total is a String
                    orderItemEntity.setOrder(orderEntity); // Set the relationship with the order
                    return orderItemEntity;
                })
                .collect(Collectors.toList());

        // Set the order items to the order entity
        orderEntity.setOrderItems(orderItemEntities);
        logger.info("OrderItemEntity created ");

        // Save the order entity
        orderDAO.save(orderEntity);
        logger.info("OrderEntity saved ");
    }

}
