package lk.ijse.gdse.aad67.posbackendspring.util;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;


    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
}
