package com.ty.fabrico.fabrico_springboot.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class WeaverProduct {

	@Id
	@GenericGenerator(name="id_genertion",strategy = "com.ty.fabrico.fabrico_springboot.customgeneration.WeaverProductCustomId")
	@GeneratedValue(generator = "id_genertion")
	String wpId;
	@NotNull
	String productName;
	@NotNull
	double productPrice;
	@NotNull
	private int quantity;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn
//	private Weaver weaver;


}
