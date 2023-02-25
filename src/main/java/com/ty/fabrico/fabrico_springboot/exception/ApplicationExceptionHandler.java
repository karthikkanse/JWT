package com.ty.fabrico.fabrico_springboot.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.fabrico.fabrico_springboot.util.ResponseStructure;
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchIdFoundExceptionHandler(NoSuchIdFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No Such ID is Present");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchUsernameFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchUsernameFoundExceptionHandler(NoSuchUsernameFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("User Name Not Found");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordIncorrectException.class)
	public ResponseEntity<ResponseStructure<String>> passwordIncorrectExceptionHandler(PasswordIncorrectException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid Password");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNameAlreadyExists.class)
	public ResponseEntity<ResponseStructure<String>> userNameAlreadyExistsExceptionHandler(UserNameAlreadyExists exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("UserName or Email-id Already Exists Try With Diffrent UserName or Email-id");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> cartNotFoundExceptionHandler( CartNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Cart not Found to add Products");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
//	@ExceptionHandler( ConstraintViolationException.class)
//	public ResponseEntity<ResponseStructure<String>>ConstraintViolationExceptionHandler(ConstraintViolationException exception){
//		ResponseStructure<String>responseStructure=new ResponseStructure<String>();
//		ResponseEntity<ResponseStructure<String>>responseEntity;
//		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//		responseStructure.setMessage("Enter proper fields");
//		responseStructure.setData(exception.getMessage());
//		return responseEntity= new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
//
//
//	}
		
	@Override
	protected ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers,HttpStatus status,WebRequest webRequest){
		List<ObjectError>objectErrors=exception.getAllErrors();
		Map<String,String>map=new LinkedHashMap<>();
		for(ObjectError error:objectErrors) {
			String message=error.getDefaultMessage();
			String field=((FieldError)error).getField();
			map.put(message, field);
		}
		ResponseStructure<Map<String,String>>responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Field Validation Error");
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setData(map);

		return new ResponseEntity<Object>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(value =  MethodArgumentNotValidException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	public ResponseEntity handleValidationException(MethodArgumentNotValidException exception){
		
//		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
//		ResponseEntity<ResponseStructure<String>> responseEntity;
//		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
//		responseStructure.setMessage("Please fill the field");
//		responseStructure.setData(exception.getMessage());
//		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
//		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler(NoOfQuantitesUnableToAdd.class)
	public ResponseEntity<ResponseStructure<String>> noOfQuantitesUnableToAddExceptionHandler(NoOfQuantitesUnableToAdd exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("No of Quantites Unavilable");
		responseStructure.setData(exception.getMessage());
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_ACCEPTABLE);
	}
}
