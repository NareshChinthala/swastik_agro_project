package com.jsp.agro.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EmailWrongException;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.PasswordWrongException;
import com.jsp.agro.util.ResponseStructure;


@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private JavaMailSender mailsender;
	
	//register

	public ResponseEntity<ResponseStructure<User>> register(User user) {

		ResponseStructure<User> m = new ResponseStructure<User>();
		m.setData(dao.save(user));
		m.setMessage("user saved Succesfully");
		m.setStatus(HttpStatus.CREATED.value());
		
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("naresh2000ch@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Registration Mail");
		message.setText(" Registration Successfull...Thank you for registering...!!!");
		mailsender.send(message);
		return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);

	}
	
	// otp send	
	public ResponseEntity<ResponseStructure<Integer>> sendOTP(String email){
		Random r=new Random(1000);
		int otp=r.nextInt(999999);
		ResponseStructure<Integer> m = new ResponseStructure<Integer>();
		m.setData(otp);
		m.setMessage("OTP sent Succesfully");
		m.setStatus(HttpStatus.FOUND.value());
		
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("naresh2000ch@gmail.com");
		message.setTo(email);
		message.setSubject(" OTP verification");
		message.setText(" Your Password reset OTP is"+otp);
		mailsender.send(message);
		return new ResponseEntity<ResponseStructure<Integer>>(m,HttpStatus.FOUND);
		
		
	}
	
	//update

	public ResponseEntity<ResponseStructure<User>> update(User user) {
		
		User db = dao.fetchById(user.getId());
		if(db!=null) {
		ResponseStructure<User>m=new ResponseStructure<User>();
		m.setData(dao.update(user));
		m.setMessage("user updated successfully");
		m.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND);
		}
		else
			throw new IdNotFoundException();
		
	}
	
	
	//delete
	

	
	public ResponseEntity<ResponseStructure<User>> delete(int id){
		User db = dao.fetchById(id);
		if(db!=null) {
			ResponseStructure<User> m= new ResponseStructure<User>();
			m.setData(dao.delete(id));
			m.setMessage("User deleted Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND) ;
		}else {
			throw new IdNotFoundException();
		}
	}
	
	//fetch by id
	
	public ResponseEntity<ResponseStructure<User>> fetchById(int id){
		User db=dao.fetchById(id);
		if(db!=null) {
			ResponseStructure<User> m= new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("User found Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND) ;
		}
		else {
			throw new IdNotFoundException();		}
	}
	
	//login

	public ResponseEntity<ResponseStructure<User>> login(User user) {
		User db = dao.fetchByEmail(user.getEmail());
		if(db!=null) {
			if(db.getPassword().equals(user.getPassword())) {
				 ResponseStructure<User> structure = new ResponseStructure<User>();
				 structure.setData(db);
				 structure.setMessage("login Sucessfull");
				 structure.setStatus(HttpStatus.FOUND.value());
				 
				 SimpleMailMessage message=new SimpleMailMessage();
					message.setFrom("naresh2000ch@gmail.com");
					message.setTo(user.getEmail());
					message.setSubject("Login Mail");
					message.setText(" Login Successfull.");
					mailsender.send(message);

					
				 return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
			}
			throw new PasswordWrongException("Password incorrect");
		}
		throw new EmailWrongException("wrong email "+user.getEmail());
	}
	
	
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(User user){
		List<User> db = dao.fetchAll();
			ResponseStructure<List<User>>u=new ResponseStructure<List<User>>();
			u.setData(db);
			u.setMessage("Gettting all objects successfully....");
			u.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(u, HttpStatus.FOUND);
		
	}
}
