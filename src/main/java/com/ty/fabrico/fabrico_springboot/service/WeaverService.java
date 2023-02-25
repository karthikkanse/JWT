package com.ty.fabrico.fabrico_springboot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.fabrico.fabrico_springboot.controller.WeaverProductController;
import com.ty.fabrico.fabrico_springboot.dao.WeaverDao;
import com.ty.fabrico.fabrico_springboot.dao.WeaverProductDao;
import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.dto.WeaverProduct;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchIdFoundException;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchUsernameFoundException;
import com.ty.fabrico.fabrico_springboot.exception.PasswordIncorrectException;
import com.ty.fabrico.fabrico_springboot.exception.UserNameAlreadyExists;
import com.ty.fabrico.fabrico_springboot.repository.WeaverProductRepository;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

@Service
public class WeaverService {

	private static final Logger LOGGER = Logger.getLogger(WeaverService.class);

	@Autowired
	private WeaverDao weaverDao;

	@Autowired
	private WeaverProductService productService;

	public ResponseEntity<ResponseStructure<Weaver>> saveWeaver(Weaver weaver) {
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		Weaver weaver2 = weaverDao.getWeaverByName(weaver.getUsername());
		if (weaver2 == null) {
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Saved");
			responseStructure.setData(weaverDao.saveWeaver(weaver));
			LOGGER.debug("Weaver saved");
			ResponseEntity<ResponseStructure<Weaver>> responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(
					responseStructure, HttpStatus.CREATED);
			return responseEntity;
		} else {
			LOGGER.error("Tried to signUp with existing user name");
			throw new UserNameAlreadyExists("User Name Already Exists Sign In With Different Name");
		}
	}

	public ResponseEntity<ResponseStructure<Weaver>> getWeaverById(String weaverid) {
		Optional<Weaver> optional = weaverDao.getWeaverById(weaverid);
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		ResponseEntity<ResponseStructure<Weaver>> responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(
				responseStructure, HttpStatus.OK);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(optional.get());
			LOGGER.debug("Weaver Found");
			return responseEntity;
		} else {
			LOGGER.error("Weaver not found");
			throw new NoSuchIdFoundException("No Such Id Found");
		}
	}

	public ResponseEntity<ResponseStructure<Weaver>> updateWeaver(Weaver weaver, String weaverid) {
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		ResponseEntity<ResponseStructure<Weaver>> responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(
				responseStructure, HttpStatus.OK);
		Optional<Weaver> optional = weaverDao.getWeaverById(weaverid);
		if (optional.isPresent()) {
			List<WeaverProduct> list = optional.get().getWeaverProduct();
			for (WeaverProduct weaverProduct : weaver.getWeaverProduct()) {
				list.add(weaverProduct);
			}
			optional.get().setWeaverProduct(list);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(weaverDao.updateWeaver(optional.get()));

			LOGGER.debug("Weaver Updated");
			return responseEntity;
		} else {
			LOGGER.error("Weaver not found to update");
			throw new NoSuchIdFoundException("Unable to Update No Such Id Found");
		}
	}

	public ResponseEntity<ResponseStructure<Weaver>> deleteWeaver(String weaverid) {
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		ResponseEntity<ResponseStructure<Weaver>> responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(
				responseStructure, HttpStatus.OK);
		Optional<Weaver> optional = weaverDao.getWeaverById(weaverid);
		if (optional.isPresent()) {
			weaverDao.deleteWeaver(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
			responseStructure.setData(optional.get());
			LOGGER.warn("Weaver deleted");
			return responseEntity;
		} else {
			LOGGER.error("Weaver not found to delete");
			throw new NoSuchIdFoundException("No Such Id Found Unable to Delete");
		}
	}

	public ResponseEntity<ResponseStructure<Weaver>> weaverLogin(Weaver weaver) {
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		ResponseEntity<ResponseStructure<Weaver>> responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(
				responseStructure, HttpStatus.OK);
		Weaver weaver2 = weaverDao.getWeaverByName(weaver.getUsername());
		if (weaver2 != null) {
			if (weaver.getPassword().equals(weaver2.getPassword())) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Login Successfull");
				responseStructure.setData(weaver2);
				LOGGER.debug("Login successfull");
				return responseEntity;
			} else {
				LOGGER.error("Invalid password");
				throw new PasswordIncorrectException();
			}
		} else {
			LOGGER.error("Invalid Mail-Id");
			throw new NoSuchUsernameFoundException();
		}
	}

}
