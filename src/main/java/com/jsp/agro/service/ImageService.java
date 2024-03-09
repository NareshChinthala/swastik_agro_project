package com.jsp.agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.ImageNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImageDao dao;
	@Autowired
	private UserDao udao;

	ResponseStructure<Image> m = new ResponseStructure<Image>();

	public ResponseEntity<ResponseStructure<Image>> uploadImage(int id, MultipartFile file) throws IOException {
		User user = udao.fetchById(id);
		if (user != null) {
			Image image = new Image();
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			user.setImage(image);
			udao.update(user);
			m.setMessage("Image upload Successfully...");
			m.setData(image);
			m.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Image>>(m, HttpStatus.ACCEPTED);
		} else {
			throw new IdNotFoundException();
		}

	}

//	public ResponseEntity<ResponseStructure<Image>> fetchImage(int id) {
//		Image image = dao.fecthImage(id);
//		if (image != null) {
//			m.setMessage("image fetched successfully...");
//			m.setStatus(HttpStatus.FOUND.value());
//			m.setData(image);
//			return new ResponseEntity<ResponseStructure<Image>>(m, HttpStatus.FOUND);
//		} else {
//			throw new ImageNotFoundException("image not found with id" + id);
//		}
//	}
	
	
	public ResponseEntity<byte[]> fetchImageById(int id){
		Image image=dao.fecthImage(id);
		if(image!=null) {
			byte[] imageBytes= image.getData();
			HttpHeaders headers= new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(imageBytes,headers,HttpStatus.FOUND);
		}
		else {
			throw new ImageNotFoundException("Image not found with id: "+id);
		}
	}
	
	
	

	public ResponseEntity<ResponseStructure<Image>> updateImage(int id, MultipartFile file) throws IOException {
		Image image = dao.fecthImage(id);
		image.setId(id);
		image.setName(file.getOriginalFilename());
		image.setData(file.getBytes());
		dao.updateImage(image);
		m.setMessage("Image updated successfully...");
		m.setStatus(HttpStatus.ACCEPTED.value());
		m.setData(image);
		return new ResponseEntity<ResponseStructure<Image>>(m, HttpStatus.ACCEPTED);
	}
	

	
	
	
	public ResponseEntity<ResponseStructure<String>> deleteImageById(int id){
		ResponseStructure<String> rss= new ResponseStructure<String>();
		Image image=dao.fecthImage(id);
		if(image!=null) {
			User img=udao.findByImage(image);
			if(img!=null) {
				img.setImage(null);
				udao.update(img);
			}
			dao.deleteImage(image);
			rss.setMessage("Image deleted successfully");
			rss.setStatus(HttpStatus.FOUND.value());
			rss.setData(image.getName()+" Deleted Successfully");
			return new ResponseEntity<ResponseStructure<String>>(rss,HttpStatus.FOUND);
		}
		else {
			throw new ImageNotFoundException("Image not found with id: "+id);
		}
	}
}
