package com.ty.fabrico.fabrico_springboot.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Weaver {

	@Id
	@GenericGenerator(name="id_genertion",strategy = "com.ty.fabrico.fabrico_springboot.customgeneration.WeaverCustomId")
	@GeneratedValue(generator = "id_genertion")
	private String weaverid;	
    @NotBlank(message = "WeaverName should not be empty")
	private String weavername;
	@NotEmpty(message = "Username should not be empty")
	@Email
	@Column(unique = true)
	@Convert(converter=AesEncryption.class)
	private String username;
	@NotEmpty(message = "Password should not be empty")
	@Convert(converter=AesEncryption.class)
	private String password;
	@Digits(integer=10,fraction=0,message = "Phone Number should be of 10 digits")
	@Min(value=999999999,message = "Phone Number should be of 10 digits")
	@Max(value=9999999999l,message = "Phone Number should be of 10 digits")
	@Convert(converter=AesEncryption.class)
	private long phone;
	@NotBlank(message = "Address should not be empty")
	@Convert(converter=AesEncryption.class)
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<WeaverProduct> weaverProduct;
	
}
