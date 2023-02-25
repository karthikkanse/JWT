package com.ty.fabrico.fabrico_springboot.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.repository.CartRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CartControllerTest {

	@Autowired
	CartRepository cartRepository;

	@Test
	@Order(1)
	void testSaveCart() {
		Cart cart = new Cart();
		cart.setCartId("1");
		cart.setCartName("isa cart");

		cartRepository.save(cart);
		assertNotNull(cartRepository.findById(cart.getCartId()).get());
	}

	@Test
	@Order(2)
	void testUpdateCart() {
		Cart cart = cartRepository.findById("1").get();
		cart.setCartId("1");
		cart.setCartName("s cart");

		cartRepository.save(cart);
		assertNotNull(cartRepository.findById(cart.getCartId()).get());

	}

	@Test
	@Order(3)
	void testGetCartById() {
		cartRepository.findById("1").get();
	}

	@Test
	@Order(4)
	void testDeleteCart() {
		//Cart cart = cartRepository.findById(1).get();
		//cartRepository.deleteById(cart.getCartId());

		cartRepository.deleteById("1");
	}

}
