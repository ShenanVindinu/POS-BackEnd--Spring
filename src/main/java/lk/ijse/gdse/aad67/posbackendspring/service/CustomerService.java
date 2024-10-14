package lk.ijse.gdse.aad67.posbackendspring.service;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomerById(String cusId);

    void updateCustomerById(String cusId, CustomerDTO updatedCustomerDTO);
}
