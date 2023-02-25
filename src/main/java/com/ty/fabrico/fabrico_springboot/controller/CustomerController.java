package com.ty.fabrico.fabrico_springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.service.CustomerService;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value="Save Customer" , notes="It is used to save the Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {
		MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}
	
	@ApiOperation(value="Update Customer" , notes="It is used to update the Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {
		MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@Valid @RequestBody Customer customer,@Valid @RequestParam String customerId) {
		return customerService.updateCustomer(customer,customerId);
	}
	
	@ApiOperation(value="Fetch Customer by Id" , notes="It is used to fetch the Customer by Id")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@Valid @RequestParam String customerId) {
		return customerService.getCustomerById(customerId);
	}

	
	@ApiOperation(value="Customer Login" , notes="It is used to Customer Login")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PatchMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Customer>> customerLogin(@Valid @RequestBody Customer customer) {
		return customerService.customerLogin(customer);

	}
	
	@ApiOperation(value="Delete Customer" , notes="It is used to delete the Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@DeleteMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@Valid @RequestParam String customerId) {
		return customerService.deleteCustomer(customerId);
	}
}
