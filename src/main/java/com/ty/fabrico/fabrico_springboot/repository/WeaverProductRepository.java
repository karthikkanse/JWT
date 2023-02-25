package com.ty.fabrico.fabrico_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.fabrico.fabrico_springboot.dto.WeaverProduct;

public interface WeaverProductRepository extends JpaRepository<WeaverProduct, String> {

//	public WeaverProduct findWeaverProductById(String wpId);
}
