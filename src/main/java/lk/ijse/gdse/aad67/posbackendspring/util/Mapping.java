package lk.ijse.gdse.aad67.posbackendspring.util;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
