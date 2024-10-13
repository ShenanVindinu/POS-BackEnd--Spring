package lk.ijse.gdse.aad67.posbackendspring.dao;

import lk.ijse.gdse.aad67.posbackendspring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerEntity,String> {

}
