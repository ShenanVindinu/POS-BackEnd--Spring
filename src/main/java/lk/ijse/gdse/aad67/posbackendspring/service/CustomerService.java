package lk.ijse.gdse.aad67.posbackendspring.service;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();
}
