package com.ty.fabrico.fabrico_springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.fabrico.fabrico_springboot.dao.CustomerDao;
import com.ty.fabrico.fabrico_springboot.dao.ProductDao;
import com.ty.fabrico.fabrico_springboot.dao.WeaverDao;
import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.dto.Product;
import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.exception.CartNotFoundException;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchIdFoundException;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

@Service
public class ProductService {

	public static final Logger LOGGER = Logger.getLogger(ProductService.class);
	@Autowired
	ProductDao productDao;

	@Autowired
	WeaverDao weaverDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	CartService cartService;

	public ResponseEntity<ResponseStructure<Product>> saveProductForWeaver(Product product, int weaverid) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		ResponseEntity<ResponseStructure<Product>> responseEntity;
		Optional<Weaver> optional = weaverDao.getWeaverById(weaverid);
		Weaver weaver;
		if (optional.isPresent()) {
			LOGGER.debug("Weaver found");
			weaver = optional.get();
		} else {
			LOGGER.error("Weaver not found to add products");
			throw new NoSuchIdFoundException("No Such Id Found For Weaver");
		}
		if (weaver != null) {
			List<Product> products = weaver.getProduct();
			products.add(product);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Product Saved To Weaver");
			responseStructure.setData(productDao.saveProduct(product));
			weaverDao.updateWeaver(weaver);
			LOGGER.debug("Products add to weaver");
		}
		return responseEntity = new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> saveProductForCustomer(int productid, int customerid) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		ResponseEntity<ResponseStructure<Product>> responseEntity;
		Optional<Customer> optional = customerDao.getCustomerById(customerid);
		Optional<Product> optional2 = productDao.getProductById(productid);
		Product product;
		Customer customer;
		if (optional.isPresent()) {
			LOGGER.debug("Customer found");
			customer = optional.get();
		} else {
			LOGGER.error("Customer not found to add products");
			throw new NoSuchIdFoundException("No Such Id Found For Customer");
		}
		if (optional2.isPresent()) {
			LOGGER.debug("Product found");
			product = optional2.get();
		} else {
			LOGGER.error("Product not found to add");
			throw new NoSuchIdFoundException("No Such Id Found For Product");
		}
		if (customer != null) {
			Cart cart = customer.getCart();
			if (cart != null) {
				if (cart.getProduct() != null) {
					List<Product> products = cart.getProduct();
					products.add(product);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("Product Saved To Cart");
					responseStructure.setData(product);
					cartService.updateCart(cart, cart.getCartId());
					customerDao.updateCustomer(customer);
					LOGGER.debug("Products add to customer");
				} else {
					List<Product> products = new ArrayList<Product>();
					products.add(product);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("Product Saved To Cart");
					responseStructure.setData(product);
					cartService.updateCart(cart, cart.getCartId());
					customerDao.updateCustomer(customer);
					LOGGER.debug("Products add to customer");
				}
			} else {
				LOGGER.error("Trying to insert products to null cart");
				throw new CartNotFoundException();
			}
		}
		return responseEntity = new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int productid) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		ResponseEntity<ResponseStructure<Product>> responseEntity;
		Optional<Product> optional = productDao.getProductById(productid);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Product Found");
			responseStructure.setData(optional.get());
			LOGGER.debug("Products found");
			return responseEntity = new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Product not found");
			throw new NoSuchIdFoundException("Product Id Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int productid) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		ResponseEntity<ResponseStructure<Product>> responseEntity;
		Optional<Product> optional = productDao.getProductById(productid);
		if (optional.isPresent()) {
			productDao.deleteProduct(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
			responseStructure.setData(optional.get());
			LOGGER.warn("Product deleted");
			return responseEntity = new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Product not found to delete");
			throw new NoSuchIdFoundException("No Such Id Found Unable To Delete");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int productid) {
		Optional<Product> optional = productDao.getProductById(productid);
		Product product2;
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		ResponseEntity<ResponseStructure<Product>> responseEntity;
		if (optional.isPresent()) {
			product2 = optional.get();
		} else {
			product2 = null;
		}
		if (product2 != null) {
			product.setPId(productid);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(productDao.updateProduct(product));
			LOGGER.debug("Product updated");
			return responseEntity = new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Product not found to update");
			throw new NoSuchIdFoundException("No Such Id Found Unable To Update");
		}

	}
}
