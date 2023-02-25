package com.ty.fabrico.fabrico_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ty.fabrico.fabrico_springboot.dto.Weaver;

public interface WeaverRepository extends JpaRepository<Weaver, String>{
	
	public Weaver getWeaverByUsername(String username);

//	@Query(value =  "SELECT c from Weaver c WHERE c.weaverProduct.wpId=?1")
//	public Weaver getWeaverByProductId(int productid);
}
