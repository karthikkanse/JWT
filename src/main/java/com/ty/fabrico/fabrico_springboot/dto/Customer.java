package com.ty.fabrico.fabrico_springboot.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GenericGenerator(name="id_genertion",strategy = "com.ty.fabrico.fabrico_springboot.customgeneration.CustomerCustomId")
	@GeneratedValue(generator = "id_genertion")
	private String customerid;
	@NotBlank(message = "CustomerName should not be empty")
	private String customerName;
	@Digits(integer=10,fraction=0,message = "Phone Number should be of 10 digits")
	@Min(value=999999999,message = "Phone Number should be of 10 digits")
	@Max(value=9999999999l,message = "Phone Number should be of 10 digits")
	@Convert(converter=AesEncryption.class)
	private long phone;
	@NotEmpty(message = "E-mail should not be empty")
	@Email
	@Column(unique = true)
	@Convert(converter=AesEncryption.class)
	private String email;
	@NotEmpty(message = "Password should not be empty")
	@Convert(converter=AesEncryption.class)
	private String password;
	@NotBlank(message = "Address should not be empty")
	@Convert(converter=AesEncryption.class)
	private String address;
	private String premium;

	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
}
