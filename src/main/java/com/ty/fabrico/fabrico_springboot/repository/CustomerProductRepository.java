package com.ty.fabrico.fabrico_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.fabrico.fabrico_springboot.dto.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, String>{

//	@Query(value = "select c from CustomerProduct c where c.CustomerProduct.productName=?1")
	public CustomerProduct getCustomerProductByProductName(String productName);
	
//	public CustomerProduct findCustomerProductById(String cpId);
}
