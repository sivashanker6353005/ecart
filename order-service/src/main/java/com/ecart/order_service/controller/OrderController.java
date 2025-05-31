package com.ecart.order_service.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.order_service.dto.Orderrequest;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	public String orderRequest(@RequestBody Orderrequest orderrequest) {
		return "Orders Placed successfully ";
	}
}
