package com.jsp.agro.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.PostDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.PostNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class PostService {

	@Autowired
	private PostDao pdao;

	@Autowired
	private UserDao udao;

	ResponseStructure<Post> rs = new ResponseStructure<Post>();

	public ResponseEntity<ResponseStructure<Post>> savePost(int id, MultipartFile file, String caption, String location)
			throws IOException {
		User db = udao.fetchById(id);
		if (db != null) {
			Image image = new Image();
			image.setData(file.getBytes());
			image.setName(file.getOriginalFilename());
			Post post = new Post();
			post.setImage(image);
			post.setDate(LocalDateTime.now());
			post.setCaption(caption);
			post.setLocation(location);
			pdao.savePost(post);
			List<Post> p = new ArrayList<Post>();
			p.add(post);
			p.addAll(db.getPost());
			db.setPost(p);
			udao.update(db);
			rs.setMessage("Post uploaded successfully...");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setData(post);
			return new ResponseEntity<ResponseStructure<Post>>(rs, HttpStatus.ACCEPTED);
		} else {
			throw new IdNotFoundException("Id not found with in the database " + id);

		}
	}

	public ResponseEntity<ResponseStructure<Post>> fetchPostById(int id) {
		Post db = pdao.fetchPostById(id);
		if (db != null) {
			rs.setMessage("Post fetched successfully...");
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setData(db);
			return new ResponseEntity<ResponseStructure<Post>>(rs, HttpStatus.FOUND);
		} else {
			throw new PostNotFoundException("Post not found with the entered id" + id);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Post>> deleteById(int id){
		Post db = pdao.fetchPostById(id);
		if(db!=null) {
			List<User> u = udao.fetchAll();
			for(User user:u) {
				List<Post> post = user.getPost();
				if(post.contains(db)) {
					post.remove(db);
					udao.update(user);
					pdao.deleteById(id);
					break;
				}
			}
			ResponseStructure<Post> rs=new ResponseStructure<Post>();
			rs.setData(db);
			rs.setMessage("Post deleted successfully...");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Id not found with in the exist database :"+id);
		}
	}
	
	
	

}
