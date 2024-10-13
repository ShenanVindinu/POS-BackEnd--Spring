package lk.ijse.gdse.aad67.posbackendspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse.aad67.posbackendspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String id;

    private String name;
    private String address;
    private String salary;


    @ManyToMany
    @JoinTable(
            name = "customer_item",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemEntity> items;

}
