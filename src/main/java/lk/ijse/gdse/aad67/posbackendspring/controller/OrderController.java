package lk.ijse.gdse.aad67.posbackendspring.controller;

import lk.ijse.gdse.aad67.posbackendspring.dto.OrderDTO;
import lk.ijse.gdse.aad67.posbackendspring.exception.DataPersistException;
import lk.ijse.gdse.aad67.posbackendspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(
            @RequestParam("id") String orderId,
            @RequestParam("date") String orderDate,
            @RequestParam("customerId") String customerId,
            @RequestParam("customerName") String customerName,
            @RequestParam("customerSalary") String customerSalary,
            @RequestParam("customerAddress") String customerAddress,
            @RequestBody OrderDTO order) {
        try {

            order.setId(orderId);
            order.setOrderDate(orderDate);
            order.setCustomerId(customerId);
            order.setCustomerName(customerName);
            order.setCustomerSalary(customerSalary);
            order.setCustomerAddress(customerAddress);


            orderService.saveOrder(order);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
