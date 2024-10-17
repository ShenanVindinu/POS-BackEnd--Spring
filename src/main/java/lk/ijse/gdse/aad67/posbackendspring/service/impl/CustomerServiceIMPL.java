package lk.ijse.gdse.aad67.posbackendspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad67.posbackendspring.dao.CustomerDAO;
import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.CustomerEntity;
import lk.ijse.gdse.aad67.posbackendspring.exception.CustomerNotFoundException;
import lk.ijse.gdse.aad67.posbackendspring.exception.DataPersistException;
import lk.ijse.gdse.aad67.posbackendspring.service.CustomerService;
import lk.ijse.gdse.aad67.posbackendspring.util.AppUtil;
import lk.ijse.gdse.aad67.posbackendspring.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceIMPL.class);

    @Autowired
    private Mapping mapping;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(AppUtil.generateCustomerId());
        CustomerEntity savedUser =
                customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (savedUser == null) {
            logger.error("Customer not Saved");
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        logger.info("getAllCustomers called");
        return mapping.asCustomerDTOList(customerDAO.findAll());
    }

    @Override
    public void deleteCustomerById(String cusId) {
        Optional<CustomerEntity> foundCustomer = customerDAO.findById(cusId);
        if (foundCustomer.isEmpty()) {
            logger.error("Customer not Found");
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerDAO.deleteById(cusId);
        }
    }

    @Override
    public void updateCustomerById(String cusId, CustomerDTO updatedCustomerDTO) {
        Optional<CustomerEntity> foundCustomer = customerDAO.findById(cusId);
        if (foundCustomer.isEmpty()) {
            logger.error("Customer not Found");
            throw new CustomerNotFoundException("Customer not found");
        } else {
            foundCustomer.get().setName(updatedCustomerDTO.getName());
            foundCustomer.get().setAddress(updatedCustomerDTO.getAddress());
            foundCustomer.get().setSalary(updatedCustomerDTO.getSalary());
        }
    }
}
