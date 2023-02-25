package com.ty.fabrico.fabrico_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.service.CartService;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;

	@ApiOperation(value = "Save Cart", notes = "It is used to Save the Cart details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart, @RequestParam String customerid) {
		return cartService.saveCart(cart, customerid);
	}

	@ApiOperation(value = "Update Cart", notes = "It is used to Update the Cart details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "ok") })
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Cart>> updateCart(@RequestBody Cart cart, @RequestParam String cartid) {
		return cartService.updateCart(cart, cartid);
	}

	@ApiOperation(value = "Fetch Cart", notes = "It is used to Fetch the Cart details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Cart>> getCartById(@RequestParam String cartId) {
		return cartService.getCartById(cartId);
	}

	@ApiOperation(value = "Delete Cart", notes = "It is used to Delete the Cart details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Cart>> deleteCart(@RequestParam String cartid) {
		return cartService.deleteCart(cartid);
	}
}
