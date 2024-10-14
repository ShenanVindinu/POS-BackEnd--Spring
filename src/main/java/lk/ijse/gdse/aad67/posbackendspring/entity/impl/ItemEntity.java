package lk.ijse.gdse.aad67.posbackendspring.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lk.ijse.gdse.aad67.posbackendspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemId;

    private String itemName;
    private String itemQTY;
    private String itemPrice;


    @ManyToMany(mappedBy = "items")
    private List<CustomerEntity> customers;
}
