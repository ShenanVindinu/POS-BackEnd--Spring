package lk.ijse.gdse.aad67.posbackendspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse.aad67.posbackendspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_info")
public class OrderEntity implements SuperEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String orderId;

    private String orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

    // Additional fields for customer details
    private String customerName;
    private String customerSalary;
    private String customerAddress;
}
