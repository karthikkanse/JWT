package com.ty.fabrico.fabrico_springboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.repository.WeaverRepository;

@Repository
public class WeaverDao {

	@Autowired
	WeaverRepository weaverRepository;

	public Weaver saveWeaver(Weaver weaver) {
		return weaverRepository.save(weaver);
	}

	public Optional<Weaver> getWeaverById(String weaverid) {
		return weaverRepository.findById(weaverid);

	}

	public Weaver updateWeaver(Weaver weaver) {
		return weaverRepository.save(weaver);
	}

	public void deleteWeaver(Weaver weaver) {
		weaverRepository.delete(weaver);
	}

	public Weaver getWeaverByName(String username) {
		return weaverRepository.getWeaverByUsername(username);
	}
	
//	public void deleteWeaverProduct(int weaverid) {
//		weaverRepository.d
//	}

}
