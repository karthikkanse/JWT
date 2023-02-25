package com.ty.fabrico.fabrico_springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ty.fabrico.fabrico_springboot.dto.WeaverProduct;
import com.ty.fabrico.fabrico_springboot.repository.WeaverProductRepository;

@Repository
public class WeaverProductDao {

	@Autowired
	WeaverProductRepository productRepository;
	
	public WeaverProduct saveProduct(WeaverProduct product)
	{
		return productRepository.save(product);
	}
	
	public Optional<WeaverProduct> getProductById(String productId)
	{
		return productRepository.findById(productId);
	}
	
	public WeaverProduct updateProduct(WeaverProduct product)
	{
		return productRepository.save(product);
	}
	
	public void deleteProduct(WeaverProduct product)
	{
		 productRepository.delete(product);
		 System.out.println("done");
	}
	
	public List<WeaverProduct> getAllProduct(){
		return productRepository.findAll();
	}
}
