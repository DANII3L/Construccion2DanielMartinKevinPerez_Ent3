package tdea.construccion2.app.service;

import org.springframework.stereotype.Component;

import tdea.construccion2.app.dto.OrderDto;

@Component
public interface OrderService {
	public OrderDto createOrdern(OrderDto orderDto) throws Exception;

	public OrderDto consultOrdern(OrderDto orderDto) throws Exception;

	public void cancelOrdern(OrderDto orderDto) throws Exception;
}
