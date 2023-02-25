package com.ty.fabrico.fabrico_springboot.controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.repository.WeaverRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class WeaverControllerTest {

	@Autowired
	WeaverRepository repository;
	
	@Test
	@Order(1)
	void testSaveWeaver() {

		Weaver weaver=new Weaver();
		weaver.setWeaverid("1");
		weaver.setWeavername("Manoj");
		weaver.setUsername("Man");
		weaver.setPassword("123");
		weaver.setPhone(313212);
		weaver.setAddress("bagalkot");
		repository.save(weaver);
		assertNotNull(repository.findById("1"));	
	}

	@Test
	@Order(2)
	void testGetWeaverById() {
		assertNotNull(repository.findById("1"));
	}

	@Test
	@Order(5)
	void testDeleteWeaver() {
		repository.deleteById("1");
	}

	@Test
	@Order(3)
	void testUpdateWeaver() {
		
		Weaver weaver= repository.findById("1").get();
		
		weaver.setWeavername("Sushas");
		weaver.setUsername("SS");
		weaver.setPassword("1234");
		repository.save(weaver);
		assertNotNull(repository.findById("1"));	
	}

	@Test
	@Order(4)
	void testWeaverLogin() {
		Weaver weaver= repository.getWeaverByUsername("SS");
		assertEquals(weaver.getPassword(), "1234");
	}

}
