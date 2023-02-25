package com.ty.fabrico.fabrico_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ty.fabrico.fabrico_springboot.dto.Weaver;
import com.ty.fabrico.fabrico_springboot.helper.JwtUtil;
import com.ty.fabrico.fabrico_springboot.jwt.CustomUserDetailsService;
import com.ty.fabrico.fabrico_springboot.model.JwtResponse;

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/tokenweaver", method =RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody Weaver weaver) throws Exception{
		System.out.println(weaver);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(weaver.getUsername(), weaver.getPassword()));
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(weaver.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT" +token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
