package com.ecart.order_service.service;

import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.ecart.order_service.dto.Orderrequest;
import com.ecart.order_service.dto.orderLineItemsDto;
import com.ecart.order_service.entity.OrderLineItems;
import com.ecart.order_service.entity.Orders;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

	public static void placeOrder(Orderrequest orderrequest) {
		Orders order = new Orders();
		order.setOrdernumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems=orderrequest.getOrderLineItemsdto().stream()
		                                   .map(OrderService::mapToDto)
		                                   .toList();
		order.setOrderLineItemList(orderLineItems);
	}

	private static OrderLineItems mapToDto(orderLineItemsDto orderLineItemsDto) {
		// TODO Auto-generated method stub
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setId(orderLineItemsDto.getId());
		return orderLineItems;
	}
}
