package com.ty.fabrico.fabrico_springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.fabrico.fabrico_springboot.controller.WeaverController;
import com.ty.fabrico.fabrico_springboot.dao.CustomerDao;
import com.ty.fabrico.fabrico_springboot.dao.WeaverProductDao;
import com.ty.fabrico.fabrico_springboot.dao.WeaverDao;
import com.ty.fabrico.fabrico_springboot.dto.Cart;
import com.ty.fabrico.fabrico_springboot.dto.Customer;
import com.ty.fabrico.fabrico_springboot.dto.CustomerProduct;
import com.ty.fabrico.fabrico_springboot.dto.WeaverProduct;
import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.exception.CartNotFoundException;
import com.ty.fabrico.fabrico_springboot.exception.NoSuchIdFoundException;
import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;

@Service
public class WeaverProductService {

	public static final Logger LOGGER = Logger.getLogger(WeaverProductService.class);
	@Autowired
	WeaverProductDao productDao;

	@Autowired
	WeaverDao weaverDao;
	
	@Autowired
	CustomerDao customerDao;

	@Autowired
	CartService cartService;

	public ResponseEntity<ResponseStructure<Weaver>> saveProductForWeaver(WeaverProduct product, String weaverid) {
		ResponseStructure<Weaver> responseStructure = new ResponseStructure<Weaver>();
		ResponseEntity<ResponseStructure<Weaver>> responseEntity;
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
			List<WeaverProduct> products = weaver.getWeaverProduct();
			products.add(product);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Product Saved To Weaver");
			responseStructure.setData(weaverDao.updateWeaver(weaver));
			LOGGER.debug("Products add to weaver");
		}
		return responseEntity = new ResponseEntity<ResponseStructure<Weaver>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<WeaverProduct>> getWeaverProductById(String productid) {
		ResponseStructure<WeaverProduct> responseStructure = new ResponseStructure<WeaverProduct>();
		ResponseEntity<ResponseStructure<WeaverProduct>> responseEntity;
		Optional<WeaverProduct> optional = productDao.getProductById(productid);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Product Found");
			responseStructure.setData(optional.get());
			LOGGER.debug("Products found");
			return responseEntity = new ResponseEntity<ResponseStructure<WeaverProduct>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Product not found");
			throw new NoSuchIdFoundException("Product Id Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<WeaverProduct>> deleteWeaverProduct(String productid,String weaverid) {
		ResponseStructure<WeaverProduct> responseStructure = new ResponseStructure<WeaverProduct>();
		ResponseEntity<ResponseStructure<WeaverProduct>> responseEntity;
		Optional<WeaverProduct> optional = productDao.getProductById(productid);
		Optional<Weaver> optional1=weaverDao.getWeaverById(weaverid);
		if (optional1.isPresent()) {
			if(optional.isPresent()) {
				List<WeaverProduct> list=optional1.get().getWeaverProduct();
				int count=0;
				for (WeaverProduct weaverProduct : list) {
					if(weaverProduct.getWpId().equals(productid)) {
						count++;
						list.remove(weaverProduct);
						optional1.get().setWeaverProduct(list);
						optional1.get().setWeaverid(weaverid);
						weaverDao.updateWeaver(optional1.get());
						productDao.deleteProduct(optional.get());
						responseStructure.setStatus(HttpStatus.OK.value());
						responseStructure.setMessage("Deleted");
						responseStructure.setData(optional.get());
						LOGGER.warn("Product deleted");
					}
				}
				if(count==0) {
					throw new NoSuchIdFoundException("No Such Product Found for WeaverId "+ weaverid);
				}
			}else {
				throw new NoSuchIdFoundException("No Such Id Found For Product");
			}
			return responseEntity = new ResponseEntity<ResponseStructure<WeaverProduct>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Weaver Not Found To Delete Product");
			throw new NoSuchIdFoundException("No Such Id Found for Weaver To Delete Product");
		}
	}

	public ResponseEntity<ResponseStructure<WeaverProduct>> updateWeaverProduct(WeaverProduct product, String productid) {
		Optional<WeaverProduct> optional = productDao.getProductById(productid);
		WeaverProduct product2;
		ResponseStructure<WeaverProduct> responseStructure = new ResponseStructure<WeaverProduct>();
		ResponseEntity<ResponseStructure<WeaverProduct>> responseEntity;
		if (optional.isPresent()) {
			product2 = optional.get();
		} else {
			product2 = null;
		}
		if (product2 != null) {
			product.setWpId(productid);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(productDao.updateProduct(product));
			LOGGER.debug("Product updated");
			return responseEntity = new ResponseEntity<ResponseStructure<WeaverProduct>>(responseStructure, HttpStatus.OK);
		} else {
			LOGGER.error("Product not found to update");
			throw new NoSuchIdFoundException("No Such Id Found Unable To Update");
		}

	}
}
