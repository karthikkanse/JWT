package com.ty.fabrico.fabrico_springboot.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GenericGenerator(name="id_genertion",strategy = "com.ty.fabrico.fabrico_springboot.customgeneration.CartCustomId")
	@GeneratedValue(generator = "id_genertion")
	private String cartId;
	@NotNull
	private String cartName;
	@NotNull
	private double totalcost;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CustomerProduct> customerProduct;
	
	@OneToOne(mappedBy = "cart")//(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
	
	
	
	

}
