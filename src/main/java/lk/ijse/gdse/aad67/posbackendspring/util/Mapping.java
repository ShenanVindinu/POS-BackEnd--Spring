package lk.ijse.gdse.aad67.posbackendspring.util;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.dto.ItemDTO;
import lk.ijse.gdse.aad67.posbackendspring.dto.OrderDTO;
import lk.ijse.gdse.aad67.posbackendspring.dto.OrderItemDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.ItemEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderEntity;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;


    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> asCustomerDTOList(List<CustomerEntity> all) {
        return modelMapper.map(all, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public List<ItemDTO> asItemDTOList(List<ItemEntity> all) {
        return modelMapper.map(all, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    public OrderEntity toOrderEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }


    public OrderEntity toOrderEntityWithCustomer(OrderDTO orderDTO, CustomerEntity customer) {

        OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);


        orderEntity.setCustomer(customer);
        orderEntity.setCustomerName(orderDTO.getCustomerName());
        orderEntity.setCustomerSalary(orderDTO.getCustomerSalary());
        orderEntity.setCustomerAddress(orderDTO.getCustomerAddress());

        return orderEntity;
    }


    public List<OrderItemEntity> toOrderItemEntities(List<OrderItemDTO> itemDTOs, OrderEntity orderEntity) {

        List<OrderItemEntity> orderItemEntities = itemDTOs.stream()
                .map(itemDTO -> {
                    OrderItemEntity orderItemEntity = modelMapper.map(itemDTO, OrderItemEntity.class);
                    orderItemEntity.setOrder(orderEntity);
                    return orderItemEntity;
                })
                .collect(Collectors.toList());

        return orderItemEntities;
    }
}
