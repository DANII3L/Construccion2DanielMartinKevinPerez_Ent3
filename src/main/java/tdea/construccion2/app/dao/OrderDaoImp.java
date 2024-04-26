package tdea.construccion2.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdea.construccion2.app.dto.OrderDto;
import tdea.construccion2.app.models.Order;
import tdea.construccion2.app.repository.OrderRepository;

@Service
public class OrderDaoImp implements OrderDao {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderDto createOrder(OrderDto orderDto) throws Exception {
		Order order = orderRepository.save(new Order(orderDto));
		return new OrderDto(order);
	}

	@Override
	public boolean findOrderExist(OrderDto orderDto) throws Exception {
		return orderRepository.existsById(orderDto.getId());
	}

	public OrderDto searchOrder(OrderDto orderDto) throws Exception {
		Order order = orderRepository.findById(orderDto.getId());
		if (order == null)
			return null;
		
		return new OrderDto(order);
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
}
