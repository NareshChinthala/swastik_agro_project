package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Post;
import com.jsp.agro.repo.PostRepo;


@Repository
public class PostDao {
	
	@Autowired
	private PostRepo repo;
	
	public Post savePost(Post post) {
		return repo.save(post);
	}
	
	public Post fetchPostById(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isEmpty()) {
			return null;
		}
		else {
			return db.get();
		}
	}
	
	public Post deleteById(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isEmpty()) {
			return null;
		}else {
			repo.deleteById(id);
			return db.get();
		}
			
	}

}
