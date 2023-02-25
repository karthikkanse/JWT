package com.ty.fabrico.fabrico_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.fabrico.fabrico_springboot.dto.Product;
import com.ty.fabrico.fabrico_springboot.service.ProductService;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@ApiOperation(value="Save Product for Weaver" , notes="It is used to Save the Product Details for Weaver")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {
		MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> saveProductForWeaver(@RequestBody Product product,@RequestParam int weaverid) {
		return  productService.saveProductForWeaver(product, weaverid);
	}
	
	@ApiOperation(value="Fetch Product for Customer" , notes="It is used to Save the Product Details for Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PatchMapping
	public ResponseEntity<ResponseStructure<Product>> saveProductForCustomer(@RequestParam int productid,@RequestParam int customerid){
		return productService.saveProductForCustomer(productid, customerid);
	}
	
	@ApiOperation(value="Save Product for Customer" , notes="It is used to Fetch the Product Details for Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam int productid){
		return productService.getProductById(productid);
	}
	
	@ApiOperation(value="Update Product for Customer" , notes="It is used to Update the Product Details for Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> updateProductById(@RequestBody Product product, @RequestParam int productid){
		return productService.updateProduct(product, productid);
	}
	
	@ApiOperation(value="Delete Product for Customer" , notes="It is used to Delete the Product Details for Customer")
	@ApiResponses(value= {@ApiResponse(code=201, message="Created"),
			@ApiResponse(code=500, message="Internal Server Error"),
			@ApiResponse(code=404, message="Not Found")})
	@DeleteMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(@RequestParam int productid){
		return productService.deleteProduct(productid);
	}

}
