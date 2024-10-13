package lk.ijse.gdse.aad67.posbackendspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad67.posbackendspring.dao.CustomerDAO;
import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.CustomerEntity;
import lk.ijse.gdse.aad67.posbackendspring.exception.DataPersistException;
import lk.ijse.gdse.aad67.posbackendspring.service.CustomerService;
import lk.ijse.gdse.aad67.posbackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private Mapping mapping;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity savedUser =
                customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }
}
