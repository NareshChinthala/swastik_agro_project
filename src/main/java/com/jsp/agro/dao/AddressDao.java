package com.jsp.agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Address;
import com.jsp.agro.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;
	public Address updateAddress(Address address) {
		return repo.save(address);
	}
	
	

}
