package lk.ijse.gdse.aad67.posbackendspring.dao;

import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItemEntity, String> {
}
