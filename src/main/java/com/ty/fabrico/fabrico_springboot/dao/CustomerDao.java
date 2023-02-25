package com.ty.fabrico.fabrico_springboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerById(String customerId) {
		return customerRepository.findById(customerId);
	}

	public Customer getCustomerByEmail(String email) {

		return customerRepository.getCustomerByEmail(email);
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}
	
	public Customer getCustomerByCartId(String cartid) {
		return customerRepository.getCustomerByCartId(cartid);
	}
}
