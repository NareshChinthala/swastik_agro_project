package com.jsp.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.User;
import com.jsp.agro.service.UserService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registeruser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.register(user);
	}
	
	@GetMapping("/loginUser")
	public ResponseEntity<ResponseStructure<User>> userLogin(@RequestBody User user){
		return userService.login(user);
	}
	
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id) {
		return userService.delete(id);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<ResponseStructure<User>> update(@RequestBody User user){
		return userService.update(user);
	}
	
	@GetMapping("/fetchUser")
	public ResponseEntity<ResponseStructure<User>> fetchById(@RequestParam int id){
		return userService.fetchById(id);
		
	}
	
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(@RequestBody User user){
		return userService.fetchAll(user);
	}
	
	
	
	
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> sendotp(@RequestParam String email){
		return userService.sendOTP(email);
	}

}
