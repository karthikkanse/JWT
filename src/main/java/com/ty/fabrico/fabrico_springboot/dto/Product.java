package com.ty.fabrico.fabrico_springboot.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int pId;
	@NotNull
	String productName;
	@NotNull
	double productPrice;
	@NotNull
	private int quantity;

	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Weaver weaver;*/
	

}
