package lk.ijse.gdse.aad67.posbackendspring.controller;

import lk.ijse.gdse.aad67.posbackendspring.dto.CustomerDTO;
import lk.ijse.gdse.aad67.posbackendspring.exception.DataPersistException;
import lk.ijse.gdse.aad67.posbackendspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.saveCustomer(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}
