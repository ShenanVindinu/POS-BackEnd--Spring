package lk.ijse.gdse.aad67.posbackendspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private String id;
    private String orderDate;
    private String customerId;
    private String customerName;
    private String customerSalary;
    private String customerAddress;
    private List<OrderItemDTO> orderItems;

}
