package lk.ijse.gdse.aad67.posbackendspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private String salary;
}
