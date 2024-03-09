package com.jsp.agro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String houseNum;
	private String street;
	private String landmark;
	private String mandal;
	private String district;
	private String state;
	private int pincode;

}
