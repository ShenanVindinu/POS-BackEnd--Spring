package lk.ijse.gdse.aad67.posbackendspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.gdse.aad67.posbackendspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_item")
public class OrderItemEntity implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemId;
    private String itemName;
    private String itemPrice;
    private String orderQTY;
    private String total;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId", nullable = false)
    private OrderEntity order;
}
