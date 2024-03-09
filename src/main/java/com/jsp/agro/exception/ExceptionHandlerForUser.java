package com.jsp.agro.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.agro.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForUser {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> IdNotFoundException(IdNotFoundException e){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("User Id Not Found");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PasswordWrongException.class)
	public ResponseEntity<ResponseStructure<String>> PasswordWrongException(PasswordWrongException e){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("User password is wrong");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> EmailWrongException(EmailWrongException e){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("User email is wrong");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Email already exist");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ImageNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> invalidImage(ImageNotFoundException e){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setMessage("Invalid id");
		m.setData(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>>postNotFoundException(PostNotFoundException  e){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setMessage("Id not found with the exist database...");
		rs.setData(e.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
	}
}
