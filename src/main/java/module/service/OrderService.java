package module.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import module.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);

}
