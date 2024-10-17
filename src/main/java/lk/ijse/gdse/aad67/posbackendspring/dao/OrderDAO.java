package lk.ijse.gdse.aad67.posbackendspring.dao;

import lk.ijse.gdse.aad67.posbackendspring.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, String> {
}
