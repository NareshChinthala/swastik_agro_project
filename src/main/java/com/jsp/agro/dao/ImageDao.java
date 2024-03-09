package com.jsp.agro.dao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Image;
import com.jsp.agro.repo.ImageRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo imageRepo;
	
	
	public Image uploadImage(Image image) {
		return imageRepo.save(image);
	}

	public Image updateImage(Image image) {
		return imageRepo.save(image);
	}

	public Image fecthImage(int id) {
		Optional<Image> db = imageRepo.findById(id);
		if (db.isEmpty()) {
			return null;
		} else {
			return db.get();
		}
	}

	public void deleteImage(Image image){
		imageRepo.delete(image);
		
	}
	

}
