package com.ty.fabrico.fabrico_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.fabrico.fabrico_springboot.dto.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{

}
