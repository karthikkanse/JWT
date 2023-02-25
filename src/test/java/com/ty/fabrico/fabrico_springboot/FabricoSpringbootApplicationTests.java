package com.ty.fabrico.fabrico_springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.repository.CustomerRepository;

@SpringBootTest
class FabricoSpringbootApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void testSave() {
		Customer customer=new Customer();
		customer.setCustomerName("Umesh");
		customer.setPhone(8296);
		customer.setEmail("umesh@gmail.com");
		customer.setPassword("123");
		customer.setAddress("Bangalore");
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(customer.getCustomerid()));
	}
	
	@Test
	public void testGetCustomer()	{
		customerRepository.findById("1").get();
	}
	
	@Test
	public void testUpdateCustomer()	{
		Customer customer=customerRepository.findById("1").get();
		customer.setCustomerName("Mahesh");
		customer.setPhone(7896);
		customer.setEmail("mahesh@gmail.com");
		customer.setPassword("456");
		customer.setAddress("Mangalore");
		customerRepository.save(customer);
		assertNotNull(customerRepository.findById(customer.getCustomerid()));
	}
	
	@Test
	public void testDeleteCustomer()	{
		Customer customer=customerRepository.findById("12").get();
		customerRepository.deleteById(customer.getCustomerid());
		assertEquals(12,customer.getCustomerid());
	}
	
	@Test
	public void testLoginCustomer()	{
		String email="umesh@gmail.com";
		String password="123";
		Customer customer=customerRepository.getCustomerByEmail(email);
		assertEquals(password, customer.getPassword());
	}

}
