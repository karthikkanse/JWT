package com.ty.fabrico.fabrico_springboot.controller;

import javax.validation.Valid;

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
import com.ty.fabrico.fabrico_springboot.service.WeaverService;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("weaver")
public class WeaverController {

	@Autowired
	WeaverService weaverService;

	@ApiOperation(value = "Save Weaver", notes = "It is used to save the Weaver Details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<ResponseStructure<Weaver>> saveWeaver(@Valid @RequestBody Weaver weaver) {
		return weaverService.saveWeaver(weaver);
	}

	@ApiOperation(value = "Fetch Weaver By Id", notes = "It is used to fetch the Weaver Details by Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Weaver>> getWeaverById(@Valid @RequestParam String weaverid) {
		return weaverService.getWeaverById(weaverid);
	}

	@ApiOperation(value = "Delete Weaver", notes = "It is used to delete the Weaver Details by Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Weaver>> deleteWeaver(@Valid @RequestParam String weaverid) {
		return weaverService.deleteWeaver(weaverid);
	}

	@ApiOperation(value = "Update Weaver", notes = "It is used to update the Weaver details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Weaver>> updateWeaver(@Valid @RequestBody Weaver weaver,
			@Valid @RequestParam String weaverid) {
		return weaverService.updateWeaver(weaver, weaverid);
	}

	@ApiOperation(value = "Login for Weaver", notes = "It is used to perform login for Weaver")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") })
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Weaver>> weaverLogin(@Valid @RequestBody Weaver weaver) {
		return weaverService.weaverLogin(weaver);
	}

}
