package com.ty.fabrico.fabrico_springboot.dao;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.fabrico.fabrico_springboot.dto.CustomerProduct;
import com.ty.fabrico.fabrico_springboot.repository.CustomerProductRepository;

@Repository
public class CustomerProductDao {

	@Autowired
	CustomerProductRepository productRepository;

	public CustomerProduct saveProduct(CustomerProduct product) {
		return productRepository.save(product);
	}

	public Optional<CustomerProduct> getProductById(String productId) {
		return productRepository.findById(productId);
	}

	public CustomerProduct updateProduct(CustomerProduct product) {
		return productRepository.save(product);
	}

	public void deleteProduct(CustomerProduct product) {
		productRepository.delete(product);
	}

	public List<CustomerProduct> getAllProduct() {
		return productRepository.findAll();
	}

	public CustomerProduct getProductByName(String productName) {
		return productRepository.getCustomerProductByProductName(productName);
	}

}
