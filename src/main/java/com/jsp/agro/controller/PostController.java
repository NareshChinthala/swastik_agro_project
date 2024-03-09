package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Post;
import com.jsp.agro.service.PostService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/savepost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int id, @RequestParam MultipartFile file,
			@RequestParam String caption, @RequestParam String location) throws IOException {
		return service.savePost(id, file, caption, location);
	}

	@GetMapping("/getpost")
	public ResponseEntity<ResponseStructure<Post>> getPost(@RequestParam int id) {
		return service.fetchPostById(id);
	}

	@DeleteMapping("/deletepost")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam int id) {
		return service.deleteById(id);
	}

}
