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

import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.dto.WeaverProduct;
import com.ty.fabrico.fabrico_springboot.service.WeaverProductService;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("weaverProduct")
public class WeaverProductController {

	@Autowired
	WeaverProductService productService;

	@ApiOperation(value = "Save Product for Weaver", notes = "It is used to Save the Product Details for Weaver")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Weaver>> saveProductForWeaver(@RequestBody WeaverProduct product,
			@RequestParam String weaverid) {
		return productService.saveProductForWeaver(product, weaverid);
	}

	@ApiOperation(value = "Fetch Product for Customer", notes = "It is used to Fetch the Product Details for Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<WeaverProduct>> getProductById(@RequestParam String productid) {
		return productService.getWeaverProductById(productid);
	}

	@ApiOperation(value = "Update Product for Customer", notes = "It is used to Update the Product Details for Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<WeaverProduct>> updateProductById(@RequestBody WeaverProduct product,
			@RequestParam String productid) {
		return productService.updateWeaverProduct(product, productid);
	}

	@ApiOperation(value = "Delete Product for Customer", notes = "It is used to Delete the Product Details for Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<WeaverProduct>> deleteProductById(@RequestParam String productid,@RequestParam String weaverid) {
		return productService.deleteWeaverProduct(productid,weaverid);
	}

}
