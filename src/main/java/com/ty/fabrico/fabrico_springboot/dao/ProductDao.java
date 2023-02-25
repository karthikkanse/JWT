package com.ty.fabrico.fabrico_springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.fabrico.fabrico_springboot.dto.Product;
import com.ty.fabrico.fabrico_springboot.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	ProductRepository productRepository;
	
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public Optional<Product> getProductById(int productId)
	{
		return productRepository.findById(productId);
	}
	
	public Product updateProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public void deleteProduct(Product product)
	{
		
		 productRepository.delete(product);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
}
