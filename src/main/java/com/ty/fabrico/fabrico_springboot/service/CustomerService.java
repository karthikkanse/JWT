package com.ty.fabrico.fabrico_springboot.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.fabrico.fabrico_springboot.dao.CustomerDao;
import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchIdFoundException;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchUsernameFoundException;
import com.ty.fabrico.fabrico_springboot.exception.PasswordIncorrectException;
import com.ty.fabrico.fabrico_springboot.exception.UserNameAlreadyExists;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;


@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	private static final Logger LOGGER=Logger.getLogger(CustomerService.class);
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer2=customerDao.getCustomerByEmail(customer.getEmail());
		ResponseEntity<ResponseStructure<Customer>> responseEntity;
		if(customer2==null) {
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Saved");
		responseStructure.setData(customerDao.saveCustomer(customer));
		LOGGER.debug("Customer Saved");
		return responseEntity = new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
		}else {
			LOGGER.error("Tried to signUp with existing email-id");
			throw new UserNameAlreadyExists("Email-id Already Exists Sign In With Different mail-id");
		}
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer, String customerId) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		ResponseEntity<ResponseStructure<Customer>> responseEntity;
		Optional<Customer> optional = customerDao.getCustomerById(customerId);
		if (optional.isPresent()) {
			customer.setCustomerid(customerId);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(customerDao.updateCustomer(customer));
			LOGGER.debug("Customer Updated");
			return responseEntity = new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else
			LOGGER.error("Customer Not Found to Update");
			throw new NoSuchIdFoundException("No Id Found to Update");
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(String customerId) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		ResponseEntity<ResponseStructure<Customer>> responseEntity;
		Optional<Customer> optional = customerDao.getCustomerById(customerId);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(optional.get());
			LOGGER.debug("Customer Found");
			return responseEntity = new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else
			LOGGER.error("Customer Not Found");
			throw new NoSuchIdFoundException("No such Id is Found");

		}
	

	public ResponseEntity<ResponseStructure<Customer>> customerLogin(Customer customer) {

		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		ResponseEntity<ResponseStructure<Customer>> responseEntity;
		Customer customer1 = customerDao.getCustomerByEmail(customer.getEmail());
		if (customer1 != null) {
			if (customer1.getPassword().equals(customer.getPassword())) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Login Successful as Customer");
				responseStructure.setData(customer1);
				LOGGER.debug("Login Successfull");
				return responseEntity = new ResponseEntity<ResponseStructure<Customer>>(responseStructure,
						HttpStatus.OK);
			} else
				LOGGER.error("Invalid Password");
				throw new PasswordIncorrectException("Invalid Password");
		} else
			LOGGER.error("Invalid Mail-Id");
			throw new NoSuchUsernameFoundException("Email Not Found");

	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(String customerId) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		ResponseEntity<ResponseStructure<Customer>> responseEntity;
		Optional<Customer> optional = customerDao.getCustomerById(customerId);
		if (optional.isPresent()) {
			customerDao.deleteCustomer(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
			responseStructure.setData(optional.get());
			LOGGER.debug("Customer Deleted");
			return responseEntity = new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		} else
			LOGGER.error("Customer Not Found to Delete");
			throw new NoSuchIdFoundException("No Id found to Delete");
	}
}