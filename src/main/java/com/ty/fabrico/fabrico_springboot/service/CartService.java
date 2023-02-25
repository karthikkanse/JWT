package com.ty.fabrico.fabrico_springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.fabrico.fabrico_springboot.dao.CartDao;
import com.ty.fabrico.fabrico_springboot.dao.CustomerDao;
import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.dto.CustomerProduct;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchIdFoundException;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

@Service
public class CartService {

	private static final Logger LOGGER = Logger.getLogger(CartService.class);

	@Autowired
	private CartDao cartDao;

	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Cart>> saveCart(Cart cart, String customerid) {
		ResponseEntity<ResponseStructure<Cart>> responseEntity;
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Optional<Customer> optional = customerDao.getCustomerById(customerid);
		Customer customer;
		if (optional.isPresent()) {
			customer = optional.get();
		} else {
			customer = null;
		}

		if (customer != null) {
			if (customer.getCart() != null) {
				deleteCart(customer.getCart().getCartId());
			}
			customer.setCart(cart);
			cart.setCustomer(customer);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("saved");
			responseStructure.setData(cartDao.saveCart(cart));
			LOGGER.debug("cart saved");

		} else {
			LOGGER.error("customer not found");
			throw new NoSuchIdFoundException("No Such Id Found For Customer");
		}
		return responseEntity = new ResponseEntity<ResponseStructure<Cart>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Cart>> updateCart(Cart cart, String cartId) {
		ResponseEntity<ResponseStructure<Cart>> responseEntity;
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Optional<Cart> optional = cartDao.getCartById(cartId);
		if (optional.isPresent()) {
			cart.setCartId(cartId);
			List<CustomerProduct> products = cart.getCustomerProduct();
			double totalcost = 0;
			int quantity = 0;
			if (products != null) {
				for (CustomerProduct products2 : products) {
					totalcost += (products2.getProductPrice() * products2.getQuantity());
					quantity += products2.getQuantity();
				}
				if (quantity >= 10 && quantity < 20) {
					totalcost = totalcost - (totalcost * 0.10);
				} else if (quantity >= 20 && quantity < 35) {
					totalcost = totalcost - (totalcost * 0.20);
				} else if (quantity >= 40) {
					totalcost = totalcost - (totalcost * 0.35);
				}
			} else {
				for (CustomerProduct products2 : optional.get().getCustomerProduct()) {
					totalcost += (products2.getProductPrice() * products2.getQuantity());
					quantity += products2.getQuantity();
				}
				if (quantity >= 10 && quantity < 20) {
					totalcost = totalcost - (totalcost * 0.10);
				} else if (quantity >= 20 && quantity < 35) {
					totalcost = totalcost - (totalcost * 0.20);
				} else if (quantity >= 40) {
					totalcost = totalcost - (totalcost * 0.35);
				}
				cart.setCustomerProduct(optional.get().getCustomerProduct());
			}

			cart.setTotalcost(totalcost);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(cartDao.updateCart(cart));
			LOGGER.debug("Cart Updated");

		} else {
			LOGGER.error("Cart not found to update");
			throw new NoSuchIdFoundException("No such id found to update");

		}
		return responseEntity = new ResponseEntity<ResponseStructure<Cart>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Cart>> getCartById(String cartId) {
		ResponseEntity<ResponseStructure<Cart>> responseEntity;
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Optional<Cart> optional = cartDao.getCartById(cartId);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(optional.get());
			LOGGER.debug("Cart found");
			return responseEntity = new ResponseEntity<ResponseStructure<Cart>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Cart not found");
			throw new NoSuchIdFoundException("No such id Found");

		}

	}

	public ResponseEntity<ResponseStructure<Cart>> deleteCart(String cartid) {
		ResponseEntity<ResponseStructure<Cart>> responseEntity;
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Optional<Cart> optional = cartDao.getCartById(cartid);

		if (optional.isPresent()) {
			Customer customer = customerDao.getCustomerByCartId(cartid);
			customer.setCart(null);
			cartDao.deleteCart(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
			responseStructure.setData(optional.get());
			LOGGER.warn("Cart deleted");
			return responseEntity = new ResponseEntity<ResponseStructure<Cart>>(responseStructure, HttpStatus.OK);

		} else {
			LOGGER.error("Cart not found to delete");
			throw new NoSuchIdFoundException("No such id found to delete");

		}
	}

}
