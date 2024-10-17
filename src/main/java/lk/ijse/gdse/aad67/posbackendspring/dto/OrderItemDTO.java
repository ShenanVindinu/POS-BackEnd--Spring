package lk.ijse.gdse.aad67.posbackendspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {

    private String itemId;
    private String itemName;
    private String itemPrice;
    private String orderQTY;
    private String total;

}
