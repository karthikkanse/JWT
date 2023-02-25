package com.ty.fabrico.fabrico_springboot.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class CustomerProduct {

	@Id
	@GenericGenerator(name="id_genertion",strategy = "com.ty.fabrico.fabrico_springboot.customgeneration.CustomerProductCustomId")
	@GeneratedValue(generator = "id_genertion")
	private String cpId;
	@NotNull
	private String productName;
	@NotNull
	private double productPrice;
	@NotNull
	private int quantity;
}
