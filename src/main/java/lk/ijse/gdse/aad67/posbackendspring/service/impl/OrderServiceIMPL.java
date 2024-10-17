package lk.ijse.gdse.aad67.posbackendspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad67.posbackendspring.dao.CustomerDAO;
import lk.ijse.gdse.aad67.posbackendspring.dao.ItemDAO;
import lk.ijse.gdse.aad67.posbackendspring.dao.OrderDAO;
import lk.ijse.gdse.aad67.posbackendspring.dao.OrderItemDAO;
import lk.ijse.gdse.aad67.posbackendspring.dto.OrderDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.ItemEntity;
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
    private ItemDAO itemDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    Mapping mapping;

    @Override
    public void saveOrder(OrderDTO orderDTO) {

        // Fetch customer from database
        CustomerEntity customer = customerDAO.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new OrderEntity object and set properties
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDTO.getId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setCustomer(customer);
        orderEntity.setCustomerName(orderDTO.getCustomerName());
        orderEntity.setCustomerSalary(orderDTO.getCustomerSalary());
        orderEntity.setCustomerAddress(orderDTO.getCustomerAddress());
        logger.info("OrderEntity created");

        // Manually create OrderItemEntities and map them directly
        List<OrderItemEntity> orderItemEntities = orderDTO.getOrderItems().stream()
                .map(itemDTO -> {

                    // Fetch the item from inventory to update its stock
                    ItemEntity itemEntity = itemDAO.findById(itemDTO.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item not found"));

                    // Check if there is enough stock for the order
                    String orderQuantityString = itemDTO.getOrderQTY();
                    String stockQuantityString = itemEntity.getItemQTY();

                    // Convert the stock and order quantity strings to integers for comparison
                    int orderQuantity = Integer.parseInt(orderQuantityString);
                    int stockQuantity = Integer.parseInt(stockQuantityString);

                    if (stockQuantity < orderQuantity) {
                        throw new RuntimeException("Insufficient stock for item " + itemDTO.getItemId());
                    }

                    // Reduce the stock by order quantity
                    int updatedStockQuantity = stockQuantity - orderQuantity;
                    itemEntity.setItemQTY(String.valueOf(updatedStockQuantity));
                    itemDAO.save(itemEntity);

                    // Map item DTO to OrderItemEntity
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setItemId(itemDTO.getItemId());
                    orderItemEntity.setItemName(itemDTO.getItemName());
                    orderItemEntity.setItemPrice(itemDTO.getItemPrice());
                    orderItemEntity.setOrderQTY(itemDTO.getOrderQTY());
                    orderItemEntity.setTotal(itemDTO.getTotal());
                    orderItemEntity.setOrder(orderEntity);

                    return orderItemEntity;
                })
                .collect(Collectors.toList());

        // Set the order items to the order entity
        orderEntity.setOrderItems(orderItemEntities);
        logger.info("OrderItemEntity created");

        // Save the order entity
        orderDAO.save(orderEntity);
        logger.info("OrderEntity saved");
    }

}
