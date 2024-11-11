package module.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

}
