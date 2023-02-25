package com.ty.fabrico.fabrico_springboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.repository.CartRepository;

@Repository
public class CartDao {

	@Autowired
	private CartRepository cartRepository;

	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	public Cart updateCart(Cart cart) {
		return cartRepository.save(cart);
	}

	public Optional<Cart> getCartById(String cartId) {
		return cartRepository.findById(cartId);
	}

	public void deleteCart(Cart cart) {
		cartRepository.delete(cart);;	}

}
