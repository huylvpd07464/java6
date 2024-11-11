package module.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import module.dao.OrderDAO;
import module.dao.OrderDetailDAO;
import module.entity.Order;
import module.entity.OrderDetail;
import module.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Override
	public Order create(JsonNode orderData) {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        
	        Order order = mapper.convertValue(orderData, Order.class);
	        if (order.getCreateDate() == null || order.getAddress() == null || order.getAccount() == null) {
	            throw new IllegalArgumentException("Thiếu thông tin bắt buộc cho đơn hàng");
	        }
	        orderDAO.save(order);
	        
	        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
	        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
	                .stream().peek(d -> {
	                    d.setOrder(order);
	                    if (d.getProduct() == null || d.getPrice() == null || d.getQuantity() == null) {
	                        throw new IllegalArgumentException("Thiếu thông tin bắt buộc cho chi tiết đơn hàng");
	                    }
	                }).collect(Collectors.toList());
	        orderDetailDAO.saveAll(details);
	        
	        return order;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi khi tạo đơn hàng", e);
	    }
	}

	@Override
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderDAO.findByUsername(username);
	}

}
