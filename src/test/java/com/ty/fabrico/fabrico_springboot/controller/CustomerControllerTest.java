package com.ty.fabrico.fabrico_springboot.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.repository.CustomerRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	@Order(1)
	void testSaveCustomer() {
		Customer customer = new Customer();
		customer.setCustomerid("1");
		customer.setCustomerName("Umesh");
		customer.setPhone(8296);
		customer.setEmail("umesh@gmail.com");
		customer.setPassword("123");
		customer.setAddress("Bangalore");
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(customer.getCustomerid()).get());

	}

	@Test
	@Order(2)
	void testUpdateCustomer() {
		Customer customer=customerRepository.findById("1").get();
		customer.setCustomerName("Mahesh");
		customer.setPhone(7896);
		customer.setEmail("mahesh@gmail.com");
		customer.setPassword("456");
		customer.setAddress("Mangalore");
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(customer.getCustomerid()).get());
	}

	@Test
	@Order(3)
	void testGetCustomerById() {
		customerRepository.findById("1").get();
	}

	@Test
	@Order(4)
	void testCustomerLogin() {
		String email="mahesh@gmail.com";
		String password="456";
		Customer customer=customerRepository.getCustomerByEmail(email);
		assertEquals(password, customer.getPassword());
	}

	@Test
	@Order(5)
	void testDeleteCustomer() {
		Customer customer=customerRepository.findById("1").get();
		customerRepository.deleteById(customer.getCustomerid());
}
}