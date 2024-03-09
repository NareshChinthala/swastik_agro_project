package com.jsp.agro.entity;

import java.time.LocalDateTime; 
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int likes;
	private LocalDateTime date;
	private String caption;
	private String location;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> listComment;
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

}
