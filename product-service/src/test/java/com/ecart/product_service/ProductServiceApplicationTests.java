package com.ecart.product_service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ecart.product_service.dto.ProductRequest;
import com.ecart.product_service.repository.Productrepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class ProductServiceApplicationTests {

	@Container
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0").withDatabaseName("ecartDB")
			.withUsername("root").withPassword("siva1997");

	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private Productrepository productrepository;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
	}

	@Test
	void shouldCreateProducts() throws Exception {
		ProductRequest productRequest = getProductRequest();
		System.out.println("JDBC URL: " + mysqlContainer.getJdbcUrl());
		mockmvc.perform(MockMvcRequestBuilders.post("/api/products").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(productRequest))).andExpect(status().isCreated());
		Assertions.assertTrue(productrepository.findAll().size() == 1);
	}

	private ProductRequest getProductRequest() {
		// TODO Auto-generated method stub
		return ProductRequest.builder().name("i phone 13").description("i phone 13").price(BigDecimal.valueOf(1300))
				.build();
	}
	
	@Test
	void shouldGetAllProducts() throws Exception {
	    // First, add a product so that we have something to retrieve
	    ProductRequest productRequest = getProductRequest();

	    mockmvc.perform(MockMvcRequestBuilders.post("/api/products")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(productRequest)))
	            .andExpect(status().isCreated());

	    // Now test GET /api/products
	    mockmvc.perform(MockMvcRequestBuilders.get("/api/products")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("i phone 13"));
	}


}
